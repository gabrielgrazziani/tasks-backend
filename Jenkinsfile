pipeline{
    agent any
    tools {
        maven 'maven'
    }
    stages{
        stage('Build Backend'){
            steps{
                sh 'mvn clean package -DskipTests=true'
            }
        }
        stage('Unit Tests'){
            steps{
                sh 'mvn test'
            }
        }
        stage('Sonar Analysis'){
            environment{
                scannerHome = tool 'SonarQube_Scanner'
            }
            steps{
                withSonarQubeEnv('sona_local'){
                    sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/src/test/**,**/model/**,**Application.java"
                }
            }
        }
        stage('Quality Gate'){
            steps{
                sleep(25)
                timeout(time: 3,unit: 'MINUTES'){
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Deploy Backend'){
            steps{
                deploy adapters: [tomcat9(credentialsId: 'ToncatLogin', path: '', url: 'http://192.168.0.22:8001')], contextPath: 'tasks-backend', onFailure: false, war: '**/*.war'
            }
        }
        stage('API Test'){
            steps{
                dir('api_test') {
                    git 'https://github.com/gabrielgrazziani/apiTest'
                    sh 'mvn test'
                }
            }
        }
        stage('Deploy Frontend'){
            steps{
                dir('frontend') {
                    git 'https://github.com/gabrielgrazziani/tasks-frontend'
                    sh 'mvn clean package'
                    deploy adapters: [tomcat9(credentialsId: 'ToncatLogin', path: '', url: 'http://192.168.0.22:8001')], contextPath: 'tasks', onFailure: false, war: '**/*.war'
                }
            }
        }
        stage('Functional Test'){
            steps{
                dir('functional_test') {
                    git 'https://github.com/gabrielgrazziani/jenkins_selenium'
                    sh 'mvn test'
                }
            }
        }
        stage('Deploy Prod'){
            steps{
                sh 'docker-compose build'
                sh 'docker-compose up -d'
            }
        }
    }
}
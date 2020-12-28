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
                sleep(15)
                timeout(time: 3,unit: 'MINUTES'){
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Deploy Backend'){
            deploy adapters: [tomcat9(credentialsId: 'ToncatLogin', path: '', url: 'http://192.168.0.22:8001')], contextPath: 'tasks-backend', onFailure: false, war: '**/*.war'
        }
    }
}
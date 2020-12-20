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
            enviroment{
                scannerHome = tool 'SONAR_SCANNER'
            }
            withSonarQubeEnv('SONAR_LOCAL'){
                sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/src/test/**,**/model/**,**Application.java"
            }
        }
    }
}
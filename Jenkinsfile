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
    }
}
pipeline{
    agent any
    tools {
        maven 'maven'
    }
    stages{
        stage('Build Backend'){
            steps{
                sh 'mvn clean packege -DskipTests=true'
            }
        }
    }
}
pipeline{
    agent any
    stages{
        stage('Build Backend'){
            steps{
                sh 'mvn clean packege -DskipTests=true'
            }
        }
    }
}
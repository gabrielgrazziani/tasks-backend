pipeline{
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v $HOME/.m2:/root/.m2'
        }
    }
    stages{
        stage('Build Backend'){
            steps{
                sh 'mvn clean packege -DskipTests=true'
            }
        }
    }
}
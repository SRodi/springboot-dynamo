pipeline {

    agent any

    environment {
         AWS_ACCESS_KEY = "${env.AWS_ACCESS_KEY}"
         AWS_SECRET_KEY = "${env.AWS_SECRET_KEY}"
    }

    stages {
        stage('compiling'){
            steps {
                sh 'mvn compile'
            }
        }
        stage('packaging'){
            steps {
                sh 'mvn package'
            }
        }
        stage('testing'){
            steps {
                sh 'mvn test'
            }
        }
    }
}
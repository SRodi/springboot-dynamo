pipeline {

    agent any

    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'dev', variable: 'AWS_ACCESS_KEY']]) {
                   sh "echo this is ${env.AWS_ACCESS_KEY}"
                   sh "echo this is ${env.AWS_SECRET_KEY}"
           }

    stages {
        stage('compiling'){
            steps {
                //sh 'mvn compile'
            }
        }
        stage('packaging'){
            steps {
                //sh 'mvn package'
            }
        }
        stage('testing'){
            steps {
                //sh 'mvn test'
            }
        }
    }
}
pipeline {
   agent any
   tools {
      // Install the Maven version configured as "M3" and add it to the path.
      maven "M3"
   }

   stages {
      def image
      stage('Build') {
         steps {
            // Get code from GitHub repository
            git branch: 'multicloud', credentialsId: 'github', url: 'https://github.com/SRodi/springboot-dynamo.git'

            // Run Maven on a Unix agent.
            sh "mvn -Dmaven.test.failure.ignore=true clean package"
         }

         post {
            // If Maven was able to run the tests, even if some of the test
            // failed, record the test results and archive the jar file.
            success {
               junit '**/target/surefire-reports/TEST-*.xml'
               archiveArtifacts 'target/*.jar'
            }
         }
      }

      stage('Build Docker image'){
        steps {
            // hardcoded project name (test)
            image = docker.build("gcr.io/pulumi-259310/sr-spring-boot-docker")
        }
        stage('Test image') {
            image.inside {
                sh 'echo "at least it runs"'
            }
        }
      }
      stage('Push to registry') {
        steps {
            withDockerRegistry(credentialsId: 'gcr:pulumi-259310', url: 'https://grc.io') {
                image.push("${env.BUILD_NUMBER}")
                image.push("v1")
            }
        }
      }
   }
}
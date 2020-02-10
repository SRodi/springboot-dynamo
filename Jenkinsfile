pipeline {
   agent any
   tools {
      // Install the Maven version configured as "M3" and add it to the path.
      maven "M3"
   }

   stages {
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

      stage('Build Docker image and Push to registry'){
        def image
        steps {
            // hardcoded project name (test)
            image = docker.build("gcr.io/pulumi-259310/sr-spring-boot-docker")
            // test image
            image.inside {
                sh 'echo "at least it runs"'
            }
            // push to registry
            withDockerRegistry(credentialsId: 'gcr:pulumi-259310', url: 'https://grc.io') {
                image.push("${env.BUILD_NUMBER}")
                image.push("v1")
            }
        }
      }
   }
}
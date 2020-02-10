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
        steps {
            def image
            withRegistry(url["https://gcr.io", credentialsId: 'gcr:pulumi-259310' ]) {
                image = build("gcr.io/pulumi-259310/sr-spring-boot-docker")
                image.push("gcr.io/pulumi-259310/sr-spring-boot-docker")

            }
        }
      }
   }
}
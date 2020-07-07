pipeline {
  agent none

  stages {
  	stage ('Build and test the project') {
	    agent {
		  docker {
		    image 'maven:3-alpine'
		    args '-v /root/.m2:/root/.m2'
		  }
		}

		stages {
		    stage('Build') {
		      steps {
		        sh 'mvn -B -DskipTests clean package'
		      }
		    }

		    stage('Test') {
		      steps {
		        sh 'mvn test'
		      }
		      post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
              }
		    }
		}
	}

    stage('Deploy') {
      when {
        branch 'master'
      }

      agent { label 'master' }

      steps {
        sshagent (credentials: ['github-ssh-key-pipeline']) {
		  sh 'ssh -o StrictHostKeyChecking=no -l jenkins paulrozhkin.ru uname -a'

		  // Copy jar file
          sh 'scp ./target/*.jar jenkins@paulrozhkin.ru:/var/www/the-contract-system/server'

          // Restart server
          sh "ssh -o StrictHostKeyChecking=no -l jenkins paulrozhkin.ru '/etc/init.d/TheContractSystemServer restart' -a"
		}
      }
    }

  }
}
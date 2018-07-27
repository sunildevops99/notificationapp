node ('puppetagentall') {
   
   stage('Fetching Git Code based on webhook push trigger') { // for display purposes
      git 'https://github.com/sunildevops99/notificationapp.git'
      mvnHome = '/usr'
   }
   stage('Build the Code') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
   stage('Copying war for docker file') {
      sh 'sudo cp /home/ubuntu/jenkins-agent/puppetagent/workspace/AppDockerDeployment/target/notificationapp-1.war /home/ubuntu/images/'
   }
   stage('Docker file from git') {
      git 'https://github.com/sunildevops99/dockerfiledeployment.git'
      sh 'sudo cp /home/ubuntu/jenkins-agent/puppetagent/workspace/AppDockerDeployment/Dockerfile /home/ubuntu/images/'
      sh 'sudo cp /home/ubuntu/jenkins-agent/puppetagent/workspace/AppDockerDeployment/SeleniumAppTest.java /opt/selenium/jars'
      sh 'sudo chmod -R 777 /home/ubuntu/images/notificationapp-1.war /home/ubuntu/images/Dockerfile'
      sh 'sudo chmod -R 777 /opt/selenium/jars/SeleniumAppTest.java'
   }
   stage('Docker Build') {
      
      sh 'cd /home/ubuntu/images'
      sh 'sudo /home/ubuntu/images/build.sh'
      
   }
   stage('Selenium Test on deployed Application') {
      sh 'cd /opt/selenium/jars'
      sh 'sudo javac -cp ".:/opt/selenium/jars/selenium-server-standalone-3.9.1.jar:/opt/selenium/jars/testng-6.0.1.jar" SeleniumAppTest.java'
      sh 'sudo chmod -R 777 /opt/selenium/jars/SeleniumAppTest.class'
      sh 'sudo java -cp ".:/opt/selenium/jars/selenium-server-standalone-3.9.1.jar:/opt/selenium/jars/testng-6.0.1.jar" SeleniumAppTest'
   }
  
}

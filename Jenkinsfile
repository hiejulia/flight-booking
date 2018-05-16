def pipeline
 
 
// Build des composants
node {

    stage ('Checkout') {
        checkout scm
        //pipeline = load 'pipeline.groovy'
        //pipeline.gitInitConf();
    }
    stage ('Build') {  
           withEnv(["PATH+MAVEN=${tool 'maven35'}/bin"]) {    
                       // Run the maven build
                       sh "mvn -Dmaven.test.skip=true clean package"
                }
            }
     stage ('Docker Build') {  
          withEnv(["PATH+docker=${tool 'docker'}"]) {    
				withDockerRegistry([credentialsId: 'docker', url: "https://hub.docker.com"]) {
				            //sh "docker build medamine123/hsbcrepo zuul-gateway";
				            //def app = docker.build medamine123/hsbcrepo 
				            //app.push 'latest'
        					//app.push version
        					sh "docker build -t medamine123/hsbcrepo:1.0.0  -f Dockerfile ./zuul-gateway"
        					sh "docker push medamine123/hsbcrepo:1.0.0"
				        }
            }
            }
            

 }
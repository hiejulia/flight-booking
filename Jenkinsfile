/**
 * Import our Jenkins shared library and execute the containerPipeline.
 *
 * The containerPipeline execuate appropriate stages for the executing
 * environment.
 *
 * This library is registered in Jenkins as a global shared library. You
 * can specify library branch - @master is incubator, something like
 * @release-1.0 will have a stable interface and receive new functionality
 * that does not break that interface.
 * More details at: https://github.com/stevetarver/jenkins-pipe
 **/
@Library('jenkins-pipe@master') _

containerPipeline([
    // Configure the environment available in the build pipeline
    environment: [
        DOCKER_CI_IMAGE: 'stevetarver/maven-java-ci:3.5.4-jdk-8-alpine-r0',
    ],
    // Configure the build pipeline
    pipeline: [
        skipCanaryStage: true,
        dockerGroup: 'stevetarver',
        //slackWorkspace: 'makaradesigngroup',
        //slackChannel: 'build',
        //slackCredentialId: 'makaradesigngroup-build-slack-token',
        test: [
            unitTestResults: 'target/surefire-reports/*.xml',
            codeCoverageHtmlDir: 'target/site/clover'
        ],
    ],
    // Identify scripts to run for each stage of the build pipeline
    stageCommands: [
        test: "mvn -Dspring.profiles.active=dev clean clover:setup test clover:aggregate clover:clover",
        package: "./jenkins/scripts/package.sh",
        deploy: "./jenkins/scripts/deploy.sh",
        integrationTest: "echo 'please implement me'",
        //integrationTest: "./integration-test/run.sh -t integration -e dev.ops",
        prodDeploy: "./jenkins/scripts/deploy.sh",
        prodTest: "echo 'please implement me'",
        //prodTest: "./integration-test/run.sh -t integration -e prod.ops",
    ]
])
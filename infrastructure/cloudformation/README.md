# Spring Boot AWS - CloudFormation

CloudFormation templates to deploy the application to AWS.

## Network template

The network CloudFormation template takes care of the following:

- Sets up a [Virtual Private Cloud](https://docs.aws.amazon.com/vpc/latest/userguide/what-is-amazon-vpc.html) (VPC)
across 3 [Availability Zones](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-regions-availability-zones.html)
(AZ) on production environments to guarantee high availability and disaster recovery. For non-production environments,
the template uses a single availability zone to save on costs.
- Creates and associates an [Internet Gateway](https://docs.aws.amazon.com/vpc/latest/userguide/VPC_Internet_Gateway.html)
to the VPC. The internet gateway allows communication between the VPC and the internet.
- Provisions 3 [Subsets](https://docs.aws.amazon.com/vpc/latest/userguide/configure-subnets.html) within each
availability zone. A public subnet for external-facing resources, a private subnet for application resources and a
private subnet for database resources. These subnets are segments of the VPC which can help reduce the risk of data
exfiltration.
- Creates and associates [Network Access Control Lists](https://docs.aws.amazon.com/vpc/latest/userguide/vpc-network-acls.html)
(Network ACL) with default rules to the public and private subnets. These network ACLs provide individual controls which
can be configured as a second layer of defense.
- Creates and associates independent [Routing Tables](https://docs.aws.amazon.com/vpc/latest/userguide/VPC_Route_Tables.html)
to each of the private subnets, which can be configured to control the flow of traffic within and outside the VPC.
Public subnets share a single routing table, because they all use the same internet gateway as the sole route to
communicate with the internet.
- Creates a [NAT gateway](https://docs.aws.amazon.com/vpc/latest/userguide/vpc-nat-gateway.html) within each of the 3
public subnets for high availability. NAT gateways allow instances in private subnets to connect to the internet or
other AWS services while they prevent the internet from initiating a connection with those instances.
- [Tags](https://docs.aws.amazon.com/tag-editor/latest/userguide/tagging.html) each resource with a name, the
application name, the environment and the subnet it is deployed to.

## Database template

The database CloudFormation template takes care of the following:

- Creates an [Aurora PostgreSQL](https://docs.aws.amazon.com/AmazonRDS/latest/AuroraUserGuide/Aurora.AuroraPostgreSQL.html)
database cluster with a primary instance and a read replica in 2 separate availability zones for production
environments. This is recommended to maintain high availability even in the unlikely event of a failure of one of the
availability zones. Aurora automatically fails over to a read replica in case the primary database instance becomes
unavailable. For non-production environments, the template creates a single database instance to save on costs.
- Places the database cluster in dedicated private subnets according to best practices.
- Creates and associates an [EC2 Security Group](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-security-groups.html)
with the database cluster. This allows locking down access to the database cluster to application instances on the
database port.
- Creates a [Database Cluster Parameter Group](https://docs.aws.amazon.com/AmazonRDS/latest/AuroraUserGuide/USER_WorkingWithDBClusterParamGroups.html)
and associates it with the database cluster. For now, the cluster parameter group uses default parameter values.
However, it is still good practice to create it to isolate our parameter settings from the default parameter group to
help prevent unwanted changes or side effects. This way, we can ensure that the database cluster is using a consistent
set of parameter values that are tailored to our specific needs.
- Creates a [Database Parameter Group](https://docs.aws.amazon.com/AmazonRDS/latest/AuroraUserGuide/USER_WorkingWithDBInstanceParamGroups.html)
and associates it with database instances. For now, the parameter group uses default parameter values. However, it is
still good practice to create it to isolate our parameter settings from the default parameter group to help prevent
unwanted changes or side effects. This way, we can ensure that database instances are using a consistent set of
parameter values that are tailored to our specific needs.
- Configures backup retention to 31 days and 7 days for production and non-production environments respectively. This
ensures a production database can be recovered to any point in time in the last 31 days. Similarly, a non-production
database can be recovered to any point in time in the last 7 days.
- Exports database logs to [CloudWatch Logs](https://docs.aws.amazon.com/AmazonCloudWatch/latest/logs/WhatIsCloudWatchLogs.html).
These logs are very useful to audit and troubleshoot database issues. With CloudWatch Logs, we can perform real-time
analysis of log data.
- Enables [Enhanced Monitoring](https://docs.aws.amazon.com/AmazonRDS/latest/AuroraUserGuide/USER_Monitoring.OS.html)
for production environments with a granularity of 60 seconds. This enables access to real-time OS metrics and
facilitates troubleshooting database performance issues.
- Enables [Performance Insight](https://aws.amazon.com/rds/performance-insights/). Sets performance insight data
retention to 31 days and 7 days for production and non-production environments respectively. Performance insight helps
to quickly detect database performance problems and determine when and where to take action.
- [Tags](https://docs.aws.amazon.com/tag-editor/latest/userguide/tagging.html) each resource with a name, the
application name, the environment and the subnet it is deployed to.

## Resources

- [GitHub - AWS CloudFormation Sample Templates](https://github.com/awslabs/aws-cloudformation-templates)
- [AWS Documentation - AWS CloudFormation Templates](https://aws.amazon.com/cloudformation/resources/templates/)
- [AWS Blog - Deploy an Amazon Aurora PostgreSQL DB cluster with recommended best practices using AWS CloudFormation](https://aws.amazon.com/blogs/database/deploy-an-amazon-aurora-postgresql-db-cluster-with-recommended-best-practices-using-aws-cloudformation/)

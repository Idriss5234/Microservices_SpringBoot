/aws-ec2-project
│── main.tf # Main configuration (calls modules or defines core infra)
│── variables.tf # Variables for reusability  
│── outputs.tf # Output values (e.g., instance IP, VPC ID)
│── provider.tf # AWS provider configuration
│── vpc.tf # VPC, Subnets, IGW, and Route Tables
│── security.tf # Security Groups
│── ec2.tf # EC2 instance definition
│── data.tf # Data sources (e.g., fetching AMI)
│── terraform.tfvars # Variable values (not committed to Git)
│── modules/
│ ├── vpc/ # VPC module (optional)
│ ├── security/ # Security group module (optional)
│ ├── ec2/ # EC2 module (optional)

resource "aws_vpc" "ms_vpc"{
    cidr_block = var.vpc_cidr_block
    tags = {
        Name = "${var.env_prefix}-vpc"
    }
}

resource "aws_subnet" "ms_subnets" {
    cidr_block = var.subnet_cidr_block
    vpc_id = aws_vpc.ms_vpc.id
    availability_zone = var.avail_zone
    tags = {
        Name = "${var.env_prefix}-subnet"
    }
}

resource "aws_internet_gateway" "ms_igw" {
    vpc_id = aws_vpc.ms_vpc.id
    tags = {
        Name: "${var.env_prefix}-igw"
    }
}

resource "aws_route_table" "ms_rtb" {
    vpc_id = aws_vpc.ms_vpc.id
    route {
        cidr_block = "0.0.0.0/0"
        gateway_id = aws_internet_gateway.ms_igw.id
    }
    tags = {
        Name = "${var.env_prefix}-rtb"
    }
}

resource "aws_route_table_association" "a-rtb-subnet" {
    subnet_id = aws_subnet.ms_subnets.id
    route_table_id= aws_route_table.ms_rtb.id
}

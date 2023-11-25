terraform {
  required_providers {
    digitalocean = {
      source  = "digitalocean/digitalocean"
      version = "~> 2.0"
    }
  }
}

resource "digitalocean_droplet" "web_server" {
  image      = "debian-12-x64"
  name       = "web-server-demo"
  region     = "nyc1"
  size       = "s-1vcpu-1gb"
}
Path : root /.ebextensions/nginx/conf.d
File : proxy.config

files:
  "/etc/nginx/conf.d/01_proxy.conf":
    mode: "000755"
    owner: root
    group: root
    content: |
      client_max_body_size 50M;
      client_body_buffer_size 16k;

container_commands:
  01_reload_nginx:
    command: "sudo service nginx reload"

FROM node:16.14.2
RUN mkdir -p /front
WORKDIR /front
COPY package*.json /front/
RUN npm install
COPY . /front/
CMD ["npm", "start"]
FROM python:3
ENV PYTHONUNBUFFERED=1

COPY . /usr/src/app

WORKDIR /usr/src/app

RUN pip install -r requirements.txt

CMD ["./run_app.sh"]

EXPOSE 8080

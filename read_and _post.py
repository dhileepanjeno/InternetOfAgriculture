import RPi.GPIO as GPIO
import Adafruit_DHT

from time import sleep, time
from firebase import firebase

GPIO.cleanup()
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

one = 19
two = 13
three = 26

GPIO.setup(one,GPIO.OUT)
GPIO.setup(two,GPIO.OUT)
GPIO.setup(three,GPIO.OUT)
GPIO.output(one, GPIO.LOW)
GPIO.output(two, GPIO.LOW)
GPIO.output(three, GPIO.LOW)
ldr_pin = 17
DHT_pin = 4
firebase = firebase.FirebaseApplication('https://agroiot-2a4a9.firebaseio.com/', None)
place = 'ct_test/sensors'
time_interval = 5
DHTsensor = sensor = Adafruit_DHT.DHT11

def read_ldr():
    GPIO.setup(ldr_pin, GPIO.OUT)
    GPIO.output(ldr_pin, GPIO.LOW)
    sleep(0.1)
    GPIO.setup(ldr_pin, GPIO.IN)
    intensity = 0
    while (GPIO.input(ldr_pin) == GPIO.LOW):
        intensity += 1
    return 20000 - intensity


def read_dht():
    humidity, temperature = Adafruit_DHT.read_retry(DHTsensor, DHT_pin)
    if humidity is not None and temperature is None:
        humidity = -1
        temperature = -1
    return temperature,humidity


def update_firebase():
    temp, hum = read_dht()
    lux = read_ldr()
    data = {"time_stamp": int(time()), "temperature": temp, "humidity": hum, "light": lux}
    print(data)
    firebase.post(place, data)

def update_actuator():
    p_data = firebase.get('ct_test/actuator', None)
    print(p_data)
    if p_data['one'] == 0:
        GPIO.output(one, GPIO.LOW)
        firebase.put('ct_test/ac_result',"one",0)
    else:
        GPIO.output(one, GPIO.HIGH)
        firebase.put('ct_test/ac_result',"one",1)
    if p_data['two'] == 0:
        GPIO.output(two, GPIO.LOW)
        firebase.put('ct_test/ac_result',"two",0)
    else:
        GPIO.output(two, GPIO.HIGH)
        firebase.put('ct_test/ac_result',"two",1)
    if p_data['three'] == 0:
        GPIO.output(three, GPIO.LOW)
        firebase.put('ct_test/ac_result',"three",0)
    else:
        GPIO.output(three, GPIO.HIGH)
        firebase.put('ct_test/ac_result',"three",1)

while True:
    update_firebase()
    update_actuator()
    sleep(time_interval)
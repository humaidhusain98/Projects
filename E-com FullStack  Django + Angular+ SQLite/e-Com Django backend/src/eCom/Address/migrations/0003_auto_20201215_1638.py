# Generated by Django 3.1.4 on 2020-12-15 11:08

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('Address', '0002_address_userid'),
    ]

    operations = [
        migrations.AlterField(
            model_name='address',
            name='userId',
            field=models.IntegerField(default=-999),
        ),
    ]

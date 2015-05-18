<img src="http://go.sap.com/_jcr_content/parHeader/header/image.adapt.tablet.png/1431534986128.png" />
<hr/>
## Уеб приложение за управление на задачите по даден софтуерен проект

### Въведение
Да се проектира и реализира уеб приложение със средствата на Java EE 6 за управление
на задачите по даден софтуерен проект.

### Изисквания към функционалността.
Характеристиките на една задача са следните: кратко име, описание, финална дата за
изпълнение, изпълнител и статус. Статусът може да бъде ‘начален’, ‘в процес’ и
‘изпълнена’. По задачите работят участниците в проекта. Един участник (или казано с
други думи: потребител) има следните характеристики: потребителско име, парола, пълно
име, email адрес и списък със задачите, по които е работил или по които работи в момента.
По всяка задача може да има коментари, като коментарите имат съдържание, дата и час и
автор. Автори могат да бъдат само потребители, регистрирани в системата.
Потребителите са два типа: обикновен и администратор. Администраторът има право да
създава други потребители и да добавя задачи по проекта. Обикновеният потребител може
да назначи на себе си или на някой друг потребител дадена задача, да променя статуса на
задача назначена на него, да вижда списъкът с потребителите и задачите, да коментира по
дадена задача.

#### Допълнителни изисквания:
<ul>
	<li>
		Ако на даден потребител му се даде нова задача, при положение, че има две или
		повече задачи в начален статус или в процес, приложението да извежда
		предупредително съобщение
	</li>
	<li>
		Когато даден някой потребител работи по дадена задача и някой от нейните
		атрибути (статус, съдържание, дата, нов коментар и т.н.) се промени от друг
		потребител, то първият потребител трябва да бъде уведомен с email. Email-ът се
		праща веднъж дневно и съдържа всички събития от деня
	</li>
	<li>
		Администраторът трябва да може да отбелязва някои задачи като важни за него. При
		вписване (login) в системата или при натискане на определен бутон, той трябва да
		може да вижда всички промени по избраните задачи
	</li>
</ul>

###Изисквания към имплементацията.
<ul>
	<li>
		1. Приложението да работи в Java EE среда
	</li>
	<li>
		2. Да се ползва Java уеб графичен потребителски интерфейс
	</li>
	<li>	
		3. Потребителите трябва да могат да достъпват системата през логин панел. На базата
		на ролята на потребителя трябва да имат следните права: Потребители,
		Администратори.
	</li>	
	<li>
		4. Като хранилище за данните да се ползва база данни.
	</li>
</ul>

###Инсталация
<ul>
	<li>
		1. <a href="http://www.apache.org/dyn/closer.cgi/tomee/tomee-1.7.1/apache-tomee-1.7.1-jaxrs.zip">Tom EE</a>
	</li>
</ul>
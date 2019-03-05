# Producer Consumer Project (JAVA)

Un importante chef lo ha contratado a usted para realizar una simulación del servicio
de   un   restaurante.   Para   ello,   se   pide   que   realice   en   Java   una   representación
computacional de dicha situación. Dada las características de la misma, es necesario
utilizar hilos, semáforos y las soluciones conocidas a los problemas productos/consumidor
y lector/escritor.

## Durante el servicio se requieren dos tipos de empleados:

1. Cocineros: 
Encargados   de   producir   los   distintos   platillos   necesarios   para
complacer a los comensales. Es necesario mencionar que tienen a su disposición
una serie de mesones de capacidades finitas, en los cuales colocan los platos una
vez que están listos. Antes de producir un platillo, un cocinero comprueba si existe
espacio en el mesón para colocarlo. Existen tres grupos de cocineros:

    a. Cocineros de Entradas:  A un cocinero le toma 0,25 horas producir 1
entrada. El mesón del cual disponen, cuenta con una capacidad inicial de
20   puestos.   Por   normativa   del   Chef,   puede   haber   un   máximo   de   3
cocineros de entradas en la cocina, con una cantidad inicial de uno.

    b. Cocineros de Platos Fuertes: A un cocinero le toma 0,33 horas producir 1
plato fuerte. El mesón del cual disponen, cuenta con una capacidad inicial
de 30 puestos. Por normativa de la Chef, puede haber un máximo de 4
cocineros de platos fuertes en la cocina, con una cantidad inicial de 2.

    c. Cocineros de Postres: A un cocinero le toma 0,30 horas producir 1 postre.
El   mesón   del   cual   disponen,   cuenta   con   una   capacidad   inicial   de   10
puestos. Por normativa del Chef, puede haber un máximo de 2 cocineros
de postres  en la cocina, con una cantidad inicial de 0.2.

2. Mesoneros:
Estos empleados tienen la responsabilidad de armar un orden, para
que luego sea enviado a una mesa. Para ello toman 3 entradas, 2 plato fuerte y 1
postre. Una vez obtenidos todos los platos, a un mesonero le toma 0,15 horas
realizar su trabajo. Al terminar, el mesonero tiene la responsabilidad de aumentar
el contador de órdenes atendidas. Puede haber un máximo de 6 mesoneros en el
restaurante.

## Además, el restaurante cuenta también con: 
3. Jefe de Mesoneros: La única tarea del jefe de mesoneros es registrar el paso de
las horas. El jefe posee un contador inicializado en el número de horas que faltan
para cerrar el restaurante. Cada hora, el cronometrador disminuye su contador en
una unidad, lo que le toma 0,05 horas. Si hay alguien leyendo el contador cuando
el jefe de mesoneros va a modificarlo, él debe esperar a que el lector termine.
Cuando el contador vaya a pasar el valor 0, el jefe lo reinicializa. Hay solo 1 jefe de
mesoneros en el restaurante y solo 1 contador. El tiempo que el jefe no esta
leyendo el contador, estará descansando.

4. Gerente: Cada hora se dirige al contador, para verificar cuántas horas faltan para
cerrar el restaurante. Si el jefe de mesoneros está modificando el contador en ese
momento, el gerente espera a que él termine antes de leer. Si el contador es
distinto a 0, el gerente va a descansar en su oficina, por un período aleatorio entre
0,45 y 2 horas. Si el contador es igual a 0, el gerente manda a pagar todas las
órdenes, reinicializando el contador, lo que le toma 0,1 horas de trabajo.

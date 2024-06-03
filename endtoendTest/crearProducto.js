import { browser } from 'k6/experimental/browser';
import { check, sleep } from 'k6';

export const options = {
    scenarios: {
        ui: {
            executor: 'shared-iterations', // para realizar iteraciones sin indicar el tiempo
            options: {
                browser: {
                    type: 'chromium',
                },
            },
        }
    },
    thresholds: {
        checks: ["rate==1.0"]
    }
}
export default async function(){
    const page = browser.newPage();
    try{

        //asignamos un usuario que es administrado y comprobamos su rol
        await page.goto('http://localhost:8080/');
        await page.waitForSelector('a[name="login"]'); // Ensure the element is available

        const submitButton = page.locator('a[name="login"]');
        await Promise.all([page.waitForNavigation(), submitButton.click()]);
        

        await page.waitForSelector('input[name="nombre"]');
        page.locator('input[name="nombre"]').type('Antonio');
        page.locator('input[name="password"]').type('123');

        const submitButtonPage = page.locator('button[type="submit"]');
        await Promise.all([page.waitForNavigation(), submitButtonPage.click()]);

        await page.waitForSelector('a[name="Productos"]');
        const ListadoButton = page.locator('a[name="Productos"]');
        await Promise.all([page.waitForNavigation(), ListadoButton.click()]);

        check(page, { 
            'cabecera es de listado productos correcta': p => p.locator('h1').textContent() === 'Nuestra mejor coleccion productoss',
        });

        // compruebo el tipo de
        await page.waitForSelector('a[name="crearProducto"]');
        const crearButton = page.locator('a[name="crearProducto"]');
        await Promise.all([page.waitForNavigation(), crearButton.click()]);

        check(page, {
            'pantalla creacion Producto': p => p.locator('h1').textContent() === 'CREAR PRODUCTO',
        });

        page.locator('input[name="nombre"]').type('Patatas fritas congeladas');
        page.locator('input[name="descripcion"]').type('surtido de las patatas fritas mas buenas y baaratas de la comarca guadalorce');
        page.locator('input[name="precio"]').type('3');
        page.locator('input[name="descripcion"]').type('patatas.png');
        
        const guardarButton = page.locator('button[type="submit"]');
        await Promise.all([page.waitForNavigation(), guardarButton.click()]);

    } finally {
        page.close();
    }
}
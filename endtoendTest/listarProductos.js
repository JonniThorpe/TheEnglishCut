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

        // compruebo el tipo de
        await page.waitForSelector('a[name="Productos"]');
        const ListadoButton = page.locator('a[name="Productos"]');
        await Promise.all([page.waitForNavigation(), ListadoButton.click()]);

        check(page, { 
            'cabecera es de listado productos correcta': p => p.locator('h1').textContent() === 'Nuestra mejor coleccion productoss',
        });


    } finally {
        page.close();
    }
}
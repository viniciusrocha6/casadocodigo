package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.models.Pedidos;

@Controller
public class PedidosServicoController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/pedidos", method = RequestMethod.GET)
	public Callable<ModelAndView> listaPedidos() {
		return () -> {
			try {
				String url = "https://book-payment.herokuapp.com/orders";
				System.out.println("Controller Passou aqui");

				Pedidos[] response = restTemplate.getForObject(url, Pedidos[].class);

				for (Pedidos pedidos : response) {
					System.out.println(pedidos.toString());
				}

				ModelAndView modelAndView = new ModelAndView("transacoes/pedidos");
				modelAndView.addObject("response", response);

				return modelAndView;
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				return new ModelAndView("redirect:/pedidos");
			}
		};

	}

}

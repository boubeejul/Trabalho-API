package com.trabalhoFinal.apiEcommerce.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalhoFinal.apiEcommerce.dto.MessageDTO;
import com.trabalhoFinal.apiEcommerce.dto.PedidoDTO;
import com.trabalhoFinal.apiEcommerce.dto.PedidoEmailDTO;
import com.trabalhoFinal.apiEcommerce.dto.ProdutoEmailDTO;
import com.trabalhoFinal.apiEcommerce.dto.ProdutoPedidoDTO;
import com.trabalhoFinal.apiEcommerce.entities.ItemPedido;
import com.trabalhoFinal.apiEcommerce.entities.Pedido;
import com.trabalhoFinal.apiEcommerce.entities.Produto;
import com.trabalhoFinal.apiEcommerce.exceptions.PedidoNotFoundException;
import com.trabalhoFinal.apiEcommerce.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	EmailService emailService;

	public List<Pedido> getAllPedidos() {
		return pedidoRepository.findAll();
	}

	public List<PedidoDTO> getAllPedidosDTO() {
		ModelMapper modelMapper = new ModelMapper();

		List<PedidoDTO> pedidosDto = new ArrayList<>();

		for (Pedido pedido : pedidoRepository.findAll()) {
			PedidoDTO novoPedidoDto = modelMapper.map(pedido, PedidoDTO.class);
			novoPedidoDto.setId_cliente(pedido.getCliente().getId_cliente());

			List<ProdutoPedidoDTO> prodPedDto = new ArrayList<>();

			for (ItemPedido itemPedido : pedido.getItemPedidos()) {
				ProdutoPedidoDTO novoProdDto = modelMapper.map(itemPedido.getProduto(), ProdutoPedidoDTO.class);
				novoProdDto.setQuantidade(itemPedido.getQuantidade());
				novoProdDto.setValor(itemPedido.getValor_liquido());
				prodPedDto.add(novoProdDto);
			}

			novoPedidoDto.setProdutos(prodPedDto);
			pedidosDto.add(novoPedidoDto);
		}

		return pedidosDto;
	}

	public Pedido getPedidoById(Integer id) {

		// ModelMapper modelMapper = new ModelMapper();

		return pedidoRepository.findById(id).orElseThrow(() -> new PedidoNotFoundException(id));

		/*
		 * PedidoDTO novoPedidoEmail = modelMapper.map(pedidoRepository.findById(id),
		 * PedidoDTO.class); Pedido emailPedido = pedidoRepository.findById(id).get();
		 * novoPedidoEmail.setId_cliente(emailPedido.getCliente().getId_cliente());
		 * 
		 * List<ProdutoPedidoDTO> prodPedDto = new ArrayList<>();
		 * 
		 * for (ItemPedido itemPedido :
		 * pedidoRepository.findById(id).get().getItemPedidos()) { ProdutoPedidoDTO
		 * novoProdDto = modelMapper.map(itemPedido.getProduto(),
		 * ProdutoPedidoDTO.class);
		 * novoProdDto.setQuantidade(itemPedido.getQuantidade());
		 * novoProdDto.setValor(itemPedido.getValor_liquido());
		 * prodPedDto.add(novoProdDto); }
		 * 
		 * novoPedidoEmail.setProdutos(prodPedDto);
		 * 
		 * emailService.enviarEmail("romuloandriolo@hotmail.com", "Pedido!",
		 * novoPedidoEmail.toString());
		 */

		// return pedidoRepository.findById(id).orElse(null);

	}

	public MessageDTO requestRelatorio(Integer id) {
		ModelMapper modelMapper = new ModelMapper();

		Optional<Pedido> pedido = pedidoRepository.findById(id);

		if (pedido != null) {
			PedidoEmailDTO pedidoEmail = modelMapper.map(pedidoRepository.findById(id), PedidoEmailDTO.class);

			List<ProdutoEmailDTO> prodPedDto = new ArrayList<>();

			for (ItemPedido itemPedido : pedidoRepository.findById(id).get().getItemPedidos()) {
				ProdutoEmailDTO novoProdDto = modelMapper.map(itemPedido.getProduto(), ProdutoEmailDTO.class);
				novoProdDto.setQuantidade(itemPedido.getQuantidade());
				novoProdDto.setValor(itemPedido.getValor_liquido());
				novoProdDto.setValor_bruto(itemPedido.getValor_bruto());
				novoProdDto.setPercentual_desconto(itemPedido.getPercentual_desconto());
				novoProdDto.setValor_liquido(itemPedido.getValor_liquido());
				novoProdDto.setUrl_imagem(itemPedido.getProduto().getArquivo().getUrl_imagem());
				prodPedDto.add(novoProdDto);
			}

			pedidoEmail.setProdutos(prodPedDto);
			pedidoEmail.setId_cliente(pedidoRepository.findById(id).get().getCliente().getId_cliente());

			emailService.enviarEmail("romuloandriolo@hotmail.com", "Pedido!", pedidoEmail);
			return new MessageDTO("Relatório enviado com sucesso!");
		} else {
			return new MessageDTO("Relatório indisponível");
		}

	}

	public Boolean savePedido(Pedido pedido) {

		LocalDate localDate = LocalDate.now();

		if (pedido.getData_pedido().isEqual(localDate) || pedido.getData_pedido().isAfter(localDate)) {
			if (pedido.getData_entrega().isEqual(pedido.getData_pedido())
					|| pedido.getData_entrega().isAfter(pedido.getData_pedido())) {
				if (pedido.getData_envio().isEqual(pedido.getData_entrega())
						|| pedido.getData_envio().isBefore(pedido.getData_entrega()))

					pedidoRepository.save(pedido);

				return true;

			}
		}

		return false;

	}

	public Pedido updatePedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	public Boolean delPedido(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).orElse(null);

		if (pedido != null) {
			pedidoRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}

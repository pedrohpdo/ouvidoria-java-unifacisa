package br.com.unifacisa.ouvidoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.unifacisa.ouvidoria.entities.Admin;
import br.com.unifacisa.ouvidoria.entities.User;
import br.com.unifacisa.ouvidoria.enums.FeedbackType;
import br.com.unifacisa.ouvidoria.services.AdminServices;
import br.com.unifacisa.ouvidoria.services.UserServices;
import br.com.unifacisa.ouvidoria.utils.Menu;
import br.com.unifacisa.ouvidoria.utils.Validate;
import br.com.unifacisa.ouvidoria.utils.Formatter;

@SpringBootApplication
public class OuvidoriaApplication implements CommandLineRunner {

	@Autowired
	AdminServices adminServices;

	@Autowired
	UserServices userServices;

	public static void main(String[] args) {
		SpringApplication.run(OuvidoriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		boolean programRunning = true;

		while (programRunning) {
			Formatter.header("Bem vindo ao Sistema de Ouvidoria", 100);
			System.out.println("1 | Cadastro");
			System.out.println("2 | Login");
			System.out.println("3 | Sair");
			System.out.println();

			int menuChoice = Validate.integerValidation("Digite: ");

			switch (menuChoice) {
				// Novo Cadastro -> User / Admin
				case 1:
					Formatter.header("NOVO CADASTRO", 100);

					System.out.println("1 | Admin");
					System.out.println("2 | Usuário");

					System.out.println();
					int cadasterChoice = Validate.integerValidation("Digite: ");
					
					Formatter.header("NOVO CADASTRO", 100);
					String NewName = Validate.stringValidation("Nome: ");
					String newEmail = Validate.stringValidation("Email: ");
					String passwordAdmin = Validate.stringValidation("Senha: ");

					switch (cadasterChoice) {
						// Cadastro -> ADMIN
						case 1:

							System.out.println(adminServices.saveAdmin(nameAdmin, emailAdmin, passwordAdmin));
							break;
							
						case 2:
						// Cadastro -> USER

							System.out.println(userServices.saveUser(username, usermail, userPassword));
							break;

						default:
							Formatter.errorEmitter("Digite uma opção válida, por favor!");
							break;
					}

					break;

				case 2:
					// Login -> USER/ADMIN
					Formatter.header("LOGIN", 100);
					String mailLogin = Validate.stringValidation("Email: ");
					String passwordLogin = Validate.stringValidation("Senha: ");

					User actualUser = userServices.checkUser(mailLogin, passwordLogin);
					Admin actualAdmin = adminServices.checkAdmin(mailLogin, passwordLogin);
					System.out.println();

					if (actualUser != null) {
						// USER LOGGED
						Formatter.successEmitter("Usuário logado com sucesso!");
						System.out.println();
						boolean userMenuRunning = true;

						while (userMenuRunning) {
							// USER LOGGED

							Formatter.line(100);
							Formatter.header("Bem vindo a Sistema, " + actualUser.getName(), 100);
							System.out.println();
							System.out.println(Menu.menuUser());
							Formatter.line(100);

							int userOption = Validate.integerValidation("Digite: ");
							System.out.println();

							switch (userOption) {
								case 1:
									// Adicionar um novo Feedback
									System.out.println("Deseja Adicionar:");
									System.out.println("1 | Reclamação");
									System.out.println("2 | Elogio");
									System.out.println("3 | Sugestão");
									Formatter.line(100);
									int typeFeedback = Validate.integerValidation("Digite: ");

									String description = Validate.stringValidation("Digite seu Feedback: ");

									switch (typeFeedback) {
										case 1:
											System.out.println(userServices.saveNewFeedback(description, "Reclamação",
													actualUser));

											break;

										case 2:
											System.out.println(userServices.saveNewFeedback(description, "Elogio", actualUser)); 
											break;

										case 3:
											System.out.println(userServices.saveNewFeedback(description, "Sugestão", actualUser));
											break;

										default:
											System.out.println("Selecione um categoria válida!");
											break;
									}
									break;

								case 2:
									Formatter.header("SEUS FEEDBACKS:", 100);
									System.out.println("Aluno: " + actualUser.getName());
									System.out.println(userServices.getFeedbacks(actualUser.getEmail()));
									break;

								case 3:
									Formatter.header("OBRIGADO POR UTILIZAR O SISTEMA, " + actualUser.getName() + "!",
											100);
									userMenuRunning = false;
									System.out.println();
									break;

								default:
									System.out.println();
									System.out.println("Selecione uma opção válida, por favor!");
									System.out.println();
									break;

							}
							System.out.println();

						}

					} else if (actualAdmin != null) {
						// admin
						Formatter.successEmitter("Admin logado com sucesso!");
						System.out.println();

						boolean adminMenuRunning = true;

						while (adminMenuRunning) {
							Formatter.line(100);
							Formatter.header("Bem vindo a Sistema, " + actualAdmin.getName(), 100);
							System.out.println();
							System.out.println(Menu.menuAdmin());
							Formatter.line(100);

							int adminOption = Validate.integerValidation("Digite: ");
							System.out.println();

							switch (adminOption) {
								case 1:

									Formatter.header("BUSCAR FEEDBACKS POR USUÁRIO", 100);
									String emailFilter = Validate.stringValidation("Email Filter: ");
									Formatter.line(100);
									System.out.println(adminServices.getUserFeedback(emailFilter));
									break;

								case 2:
									Formatter.header("TODOS OS FEEDBACKS", 100);
									System.out.println(adminServices.getAllFeedbacks());
									break;

								case 3:
									Formatter.header("DELETAR UM FEEDBACK", 100);
									System.out.println(adminServices.getAllFeedbacks());
									int idToDelete = Validate.integerValidation("Feedback ID: ");
									System.out.println(adminServices.deleteFeedbackByID((long) idToDelete));
									System.out.println();
									break;
								case 4:
									Formatter.header("DELETAR USUÁRIO POR EMAIL", 100);
									System.out.println(adminServices.getUsers());
									String emailToDelete = Validate
											.stringValidation("Digite um usuário para deletar /Email/: ");
									System.out.println(adminServices.deleteUserByEmail(emailToDelete));
									break;
								case 5:
									adminMenuRunning = false;
									System.out.println();
									break;

								default:
									System.out.println();
									System.out.println("Selecione uma opção válida, por favor!");
									System.out.println();
									break;
							}
							System.out.println();

						}

					} else {
						Formatter.errorEmitter(
								"Não foi encontrado o usuário ou administrador. Email ou Senha incorretos!");
					}
					break;
				case 3:
					programRunning = false;
					Formatter.header("ATÉ BREVE!", 100);
					break;

				default:
					Formatter.errorEmitter("Digite uma opção válida, por favor!");
					break;
			}

		}

	}
}

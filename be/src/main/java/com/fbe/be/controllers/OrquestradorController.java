package com.fbe.be.controllers;


@RestController
@RequestMapping("/clients")
public class OrquestradorControlle {

    private final ClientRepository clientRepository;

    public ClientsController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public List<BaseEntity> getClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public BaseEntity getClient(@PathVariable Long id) {
        return clientRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity createClient(@RequestBody BaseEntity client) throws URISyntaxException {
        BaseEntity savedClient = clientRepository.save(client);
        return ResponseEntity.created(new URI("/clients/" + savedClient.getId())).body(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateClient(@PathVariable Long id, @RequestBody BaseEntity client) {
        BaseEntity currentClient = clientRepository.findById(id).orElseThrow(RuntimeException::new);
        currentClient.setName(client.getName());
        currentClient.setEmail(client.getEmail());
        currentClient = clientRepository.save(client);

        return ResponseEntity.ok(currentClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
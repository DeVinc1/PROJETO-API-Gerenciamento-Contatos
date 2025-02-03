package com.connectiva.app.rest_api_connectiva.utils;

import com.connectiva.app.rest_api_connectiva.model.Address;
import com.connectiva.app.rest_api_connectiva.model.Contact;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class RequestBodyPatcher {

    /**
     * Verifica se uma propriedade enviada é nula e, nesse caso, retorna o valor da propriedade original.
     * Caso contrário, retorna o valor da propriedade enviada.
     *
     * @param <T> O tipo das propriedades sendo comparadas.
     * @param originalProperty O valor original da propriedade.
     * @param sentProperty O valor enviado da propriedade.
     * @return O valor original (se o enviado for nulo) ou o valor enviado.
     */
    public <T> T nullPropertySkipper(T originalProperty, T sentProperty){
        if(sentProperty == null){
            return originalProperty;
        } else{
            return sentProperty;
        }
    }

    /**
     * Corrige objeto Contact, combinando dados existentes e enviados, atualizando as propriedades e listas de endereços associadas.
     *
     * @param existingContact O objeto Contact existente.
     * @param sentContact O objeto Contact enviado.
     * @return O objeto Contact resultante com os dados combinados.
     */

    public Contact contactPatcher(Contact existingContact, Contact sentContact){

        sentContact = contactPropertyMerge(existingContact, sentContact);

        List<Address> addresses = new ArrayList<>();

        addresses.addAll(addressPropertyPatcher(existingContact, sentContact));
        addresses.addAll(addressObjectMerge(existingContact, sentContact));
        addresses.addAll(newAddressCatcher(sentContact));

        sentContact.setAddressesAssociated(addresses);

        return sentContact;
    }

    /**
     * Mescla as propriedades do objeto Contato enviadas com as propriedades existentes,
     * mantendo os valores originais caso as enviadas sejam nulas.
     *
     * @param existingContact O objeto Contact existente.
     * @param sentContact O objeto Contact enviado.
     * @return O objeto Contact com as propriedades mescladas.
     */
    public Contact contactPropertyMerge(Contact existingContact, Contact sentContact){
        sentContact.setId(existingContact.getId());
        sentContact.setName(nullPropertySkipper(existingContact.getName(), sentContact.getName()));
        sentContact.setEmail(nullPropertySkipper(existingContact.getEmail(), sentContact.getEmail()));
        sentContact.setPhoneNumber(nullPropertySkipper(existingContact.getPhoneNumber(), sentContact.getPhoneNumber()));
        sentContact.setBirthDate(nullPropertySkipper(existingContact.getBirthDate(), sentContact.getBirthDate()));

        return sentContact;
    }

    /**
     * Atualiza as propriedades dos endereços enviados, mantendo os dados
     * originais caso as propriedades enviadas sejam nulas.
     *
     * @param existingContact O objeto Contact com os endereços existentes.
     * @param sentContact O objeto Contact com os endereços enviados.
     * @return A lista de endereços enviados com as propriedades atualizadas.
     */
    public List<Address> addressPropertyPatcher(Contact existingContact, Contact sentContact){
        List<Address> existingAddresses = existingContact.getAddressesAssociated();
        List<Address> sentAddresses = sentContact.getAddressesAssociated();

        for(Address existingAddress : existingAddresses){
            for(Address sentAddress : sentAddresses){
                if(existingAddress.getId().equals(sentAddress.getId())){
                    sentAddress.setStreetName(nullPropertySkipper(existingAddress.getStreetName(), sentAddress.getStreetName()));
                    sentAddress.setHouseNumber(nullPropertySkipper(existingAddress.getHouseNumber(), sentAddress.getHouseNumber()));
                    sentAddress.setPostalCode(nullPropertySkipper(existingAddress.getPostalCode(), sentAddress.getPostalCode()));
                }
            }
        }

        return sentAddresses;
    }

    /**
     * Verifica e combina objetos Address de contato existentes e enviados,
     * adicionando os endereços únicos (presentes apenas no existente) à lista consolidada.
     *
     * @param existingContact O objeto Contact com os endereços existentes.
     * @param sentContact O objeto Contact com os endereços enviados.
     * @return Uma lista de endereços únicos que estão apenas no contato existente.
     */
    public List<Address> addressObjectMerge(Contact existingContact, Contact sentContact){
        List<Address> existingAddresses = existingContact.getAddressesAssociated();
        List<Address> sentAddresses = sentContact.getAddressesAssociated();
        List<Address> addressesToAdd = new ArrayList<>();


        for (int i = existingAddresses.size() - 1; i >= 0; i--) {
            Address existingAddress = existingAddresses.get(i);

            boolean isUnique = true;
            for (Address sentAddress : sentAddresses) {
                if (Objects.equals(existingAddress.getId(), sentAddress.getId())) {
                    isUnique = false;
                    break;
                }
            }

            if (isUnique) {
                addressesToAdd.add(existingAddress);
                existingAddresses.remove(i);
            }
        }

        return addressesToAdd;
    }

    public List<Address> newAddressCatcher(Contact sentContact) {
        List<Address> sentAddresses = sentContact.getAddressesAssociated();
        List<Address> newAddressesSent = new ArrayList<>();

        for (int i = sentAddresses.size() - 1; i >= 0; i--) {
            Address sentAddress = sentAddresses.get(i);
            if (sentAddress.getId() == null) {
                newAddressesSent.add(sentAddress);
                sentAddresses.remove(i);
            }
        }

        return newAddressesSent;

    }
}

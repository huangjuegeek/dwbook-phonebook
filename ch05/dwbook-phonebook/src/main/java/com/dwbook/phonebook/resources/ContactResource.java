package com.dwbook.phonebook.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.dwbook.phonebook.representations.Contact;

@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {
	@GET
	@Path("/{id}")
	public Response getContact(@PathParam("id") int id) {
		// retrieve information about the contact with the provided id
		return Response.ok(new Contact(id, "John", "Doe", "+123456789"))
				.build();
	}

	@POST
	public Response createContact(Contact contact) {
		// store the new contact
		return Response.created(null).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteContact(@PathParam("id") int id) {
		// delete the contact with the provided id
		return Response.noContent().build();
	}

	@PUT
	@Path("/{id}")
	public Response updateContact(@PathParam("id") int id, Contact contact) {
		// update the contact with the provided ID
		return Response.ok(
				new Contact(id, contact.getFirstName(), contact.getLastName(),
						contact.getPhone())).build();
	}
}

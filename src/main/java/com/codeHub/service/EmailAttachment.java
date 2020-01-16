package com.codeHub.service;

import com.sun.mail.gimap.GmailFolder;
import com.sun.mail.gimap.GmailRawSearchTerm;
import com.sun.mail.gimap.GmailStore;

import java.io.*;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;

public class EmailAttachment {


        private String saveDirectory;

        /**
         * Sets the directory where attached files will be stored.
         * @param dir absolute path of the directory
         */
        public void setSaveDirectory(String dir) {
            this.saveDirectory = dir;
        }

        /**
         * Downloads new messages and saves attachments to disk if any.
         * @param userName
         * @param password
         */
        public void downloadEmailAttachments(String userName, String password) {
            Properties properties = new Properties();

            // server setting
            properties.put("mail.store.protocol", "pop3");
            properties.put("mail.pop3.host","pop.gmail.com");
            properties.put("mail.pop3.port", 995);
            properties.put("mail.pop3.starttls.enable", "true");

            // SMTP Session
//            properties.put("mail.transport.protocol", "smtp");
//            properties.put("mail.smtp.host","smtp.gmail.com");
//            //properties.setProperty("mail.smtp.host",host );
//            properties.put("mail.smtp.port", "587");
//            properties.put("mail.smtp.auth", "true");
//            // We need TTLS, which gmail requires
//            properties.put("mail.smtp.starttls.enable","true");
//            properties.put("mail.smtp.auth", "true");


            // SSL setting
            properties.setProperty("mail.pop3.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            properties.setProperty("mail.pop3.socketFactory.fallback", "false");
            properties.setProperty("mail.pop3.socketFactory.port",
                    String.valueOf(995));

            Session session = Session.getDefaultInstance(properties,    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName,password);
                }
            });

            try {
                // Connect to the message store
                Store store = session.getStore("pop3");
                store.connect(userName, password);

                // Open the inbox folder
                Folder emailInbox = store.getFolder("inbox");
                emailInbox.open(Folder.READ_WRITE);

                // Fetch new messages from email inbox
                Message[] arrayMessages = emailInbox.getMessages();
                System.out.println("New messages: "+arrayMessages.length);

                //Process each message
                for (int i = 0; i < arrayMessages.length; i++) {
                    Message message = arrayMessages[i];
                    Address[] fromAddress = message.getFrom();
                    String from = fromAddress[0].toString();
                    String subject = message.getSubject();
                    String sentDate = message.getSentDate().toString();
                    Address[] from_address =message.getRecipients(Message.RecipientType.TO);

                    //Iterate recipients
                    System.out.println("To: ");
                    for(int j = 0; j < from_address.length; j++){
                        System.out.println(from_address[j].toString());
                    }

                    String contentType = message.getContentType();
                    String messageContent = "";

                    // store attachment file name, separated by comma
                    String attachFiles = "";

                    if (contentType.contains("multipart")) {
                        // content may contain attachments
                        Multipart multiPart = (Multipart) message.getContent();
                        int numberOfParts = multiPart.getCount();
                        for (int partCount = 0; partCount < numberOfParts; partCount++) {
                            MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                            if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                                // this part is attachment
                                String fileName = part.getFileName();
                                attachFiles += fileName + ", ";
                                part.saveFile(saveDirectory + File.separator + fileName);
                                //process file
                                BodyPart bpart = (MimeBodyPart) multiPart.getBodyPart(partCount);
                                InputStream stream=bpart.getInputStream();
                                BufferedReader br=new BufferedReader(new InputStreamReader(stream));
                                while(br.ready()){
                                    System.out.println(br.readLine()+"###");
                                }
                            } else {
                                // this part may be the message content //process email body
                                messageContent = part.getContent().toString();
                                System.out.println("###############");
                            }
                        }

                        if (attachFiles.length() > 1) {
                            attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
                        }
                    } else if (contentType.contains("text/plain")
                            || contentType.contains("text/html")) {
                        Object content = message.getContent();
                        if (content != null) {
                            messageContent = content.toString();
                            System.out.println("###$$$$$$$$$$$");
                        }
                    }

                    // print out details of each message
                    System.out.println("Message #" + (i + 1) + ":");
                    System.out.println("\t From: " + from);
                    System.out.println("\t Subject: " + subject);
                    System.out.println("\t Sent Date: " + sentDate);
                    System.out.println("\t Message: " + messageContent);
                    System.out.println("\t Attachments: " + attachFiles);
                }

                // disconnect
                emailInbox.close(true);
                store.close();
            } catch (NoSuchProviderException ex) {
                System.out.println("No provider for pop3.");
                ex.printStackTrace();
            } catch (MessagingException ex) {
                System.out.println("Could not connect to the message store");
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        public void processEmailSearch() {
            Properties properties = new Properties();

            // server setting
            properties.put("mail.store.protocol", "imap");
            properties.put("mail.imap.host", "imap.gmail.com");
            properties.put("mail.imap.port", 993);
            properties.put("mail.imap.starttls.enable", "true");

            // SSL setting
            properties.setProperty("mail.imap.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            properties.setProperty("mail.imap.socketFactory.fallback", "false");
            properties.setProperty("mail.imap.socketFactory.port",
                    String.valueOf(993));
            try {
                Session session = Session.getDefaultInstance(properties, null);
                GmailStore store = (GmailStore)session.getStore("gimap");
                store.connect("imap.gmail.com", "team@msurvey.co.ke", "mSurvey!2016");
                System.out.println("Connected");
                // Open the inbox folder
                GmailFolder emailInbox = (GmailFolder)store.getFolder("inbox");
                emailInbox.open(Folder.READ_WRITE);

                // Fetch new messages from email inbox
//                SearchTerm searchTerm = new SearchTerm() {
//                    @Override
//                    public boolean match(Message message) {
//                        try {
//                            if (message.getSubject().contains("4g capital")) {
//                                return true;
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        return false;
//                    }
//                };

                Message[] arrayMessages = emailInbox.search(new GmailRawSearchTerm("4g capital"));
                System.out.println("New messages: " + arrayMessages.length);

                //Process each message
                for (int i = 0; i < arrayMessages.length; i++) {
                    Message message = arrayMessages[i];
                    Address[] fromAddress = message.getFrom();
                    String from = fromAddress[0].toString();
                    String subject = message.getSubject();
                    String sentDate = message.getSentDate().toString();
                    Address[] from_address = message.getRecipients(Message.RecipientType.TO);

                    //Iterate recipients
                    System.out.println("To: ");
                    for (int j = 0; j < from_address.length; j++) {
                        System.out.println(from_address[j].toString());
                    }

                    String contentType = message.getContentType();
                    String messageContent = "";

                    // store attachment file name, separated by comma
                    String attachFiles = "";

                    if (contentType.contains("multipart")) {
                        // content may contain attachments
                        Multipart multiPart = (Multipart) message.getContent();
                        int numberOfParts = multiPart.getCount();
                        for (int partCount = 0; partCount < numberOfParts; partCount++) {
                            MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                            if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                                // this part is attachment
                                String fileName = part.getFileName();
                                attachFiles += fileName + ", ";
                                part.saveFile(saveDirectory + File.separator + fileName);
                                //process file
                                BodyPart bpart = (MimeBodyPart) multiPart.getBodyPart(partCount);
                                InputStream stream = bpart.getInputStream();
                                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                                while (br.ready()) {
                                    System.out.println(br.readLine() + "###");
                                }
                            } else {
                                // this part may be the message content //process email body
                                messageContent = part.getContent().toString();
                                System.out.println("###############");
                            }
                        }

                        if (attachFiles.length() > 1) {
                            attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
                        }
                    } else if (contentType.contains("text/plain")
                            || contentType.contains("text/html")) {
                        Object content = message.getContent();
                        if (content != null) {
                            messageContent = content.toString();
                            System.out.println("###$$$$$$$$$$$");
                        }
                    }

                    // print out details of each message
                    System.out.println("Message #" + (i + 1) + ":");
                    System.out.println("\t From: " + from);
                    System.out.println("\t Subject: " + subject);
                    System.out.println("\t Sent Date: " + sentDate);
                    System.out.println("\t Message: " + messageContent);
                    System.out.println("\t Attachments: " + attachFiles);
                }

                // disconnect
                emailInbox.close(true);
                store.close();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


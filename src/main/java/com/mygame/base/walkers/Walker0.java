/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame.base.walkers;

import com.mygame.base.commands.CommandMove;
import com.jme3.anim.AnimComposer;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.mygame.Pool;
import com.mygame.beni.TipoProdotto;
import com.mygame.buildings.edifici.Edificio;
import com.mygame.models.ModelManager;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author giovanni
 */
public class Walker0 {

    protected Node groupNode = new Node("GroupNode");

    protected final Edificio home;
    private static int PROG_WALKER = 0;
    protected int idWalker = 0;

    private int attachmentOfMaterial = -1;

    protected final AnimComposer control;
    protected boolean forward = true;
    protected float alpha = 0;
    protected float speed = 0.01f;
    private final Node node;

    protected Vector3f startPos;
    protected Vector3f endPos;
    private CommandMove currentCommand;
    private int delay;
    private long startWait;
    private TipoProdotto prodottoTrasportato = null;

  

    private enum Stato {
        STANDING, WALKING, WAITING
    };
    private Stato currentStato = Stato.STANDING;

    private final Queue<CommandMove> commands = new LinkedList<>();

    public Walker0(Edificio home) {
        this.node = (Node) Pool.getAssetManager().loadModel("Models/Oto/Oto.mesh.xml");
        groupNode.attachChild(node);
        groupNode.setLocalScale(0.2f);
        this.control = node.getControl(AnimComposer.class);
        control.setCurrentAction("Walk");
        Pool.getRootNode().attachChild(groupNode);

        groupNode.setLocalTranslation(home.getPosition());

        this.home = home;
        idWalker = getId();
        Pool.addWalker(this);
    }

    public void update(float tpf) {

        switch (currentStato) {
            case WAITING:
                long currTime = (new Date()).getTime();
//                if (currTime - startWait > delay * 1000) {
//                    setStato(Stato.STANDING);
//                }
                  if (currTime - startWait > delay ) {
                    setStato(Stato.STANDING);
                }

                break;
            case Stato.STANDING:

                processStanding();
                if (commands.peek() != null) {
                    //  currentCommandIndex = (currentCommandIndex + 1) % commands.size();
                    setStato(Stato.WALKING);
                    forward = true;
                    startPos = groupNode.getLocalTranslation();
                    currentCommand = commands.poll();
                    endPos = currentCommand.getDestination();

                }

                break;
            case Stato.WALKING:
                move(tpf);
                break;
        }
    }

    private void move1(float tpf) {

        Vector3f currentPos = groupNode.getLocalTranslation();
        Vector3f direction = endPos.subtract(currentPos);

        // Se siamo molto vicini, smetti di muovere
        if (direction.length() < 0.1f) {
//            System.out.println("ret");
            //        return;
        }

        direction.normalizeLocal();
        Vector3f movement = direction.mult(speed * tpf);

        // Per evitare di superare la destinazione:
        if (movement.length() > endPos.subtract(currentPos).length()) {

            groupNode.setLocalTranslation(endPos);
        } else {

            groupNode.move(movement);
        }
        boolean arrived = checkDistance();
        if (arrived) {
            setStato(Stato.STANDING);

            currentCommand.execute();
        }
    }

    private void move(float tpf) {

        if (forward) {
            alpha += tpf * speed;
            if (alpha >= 1f) {
                alpha = 1f;
                forward = false; // fine tratta, non incrementiamo più
            }
            alpha = 0.01f;
        }

        // Calcolo interpolazione tra startPos e endPos
        Vector3f interpolated = FastMath.interpolateLinear(alpha, startPos, endPos);
        groupNode.setLocalTranslation(interpolated);

        Vector3f dir = endPos.subtract(startPos).normalize();
        groupNode.lookAt(groupNode.getLocalTranslation().add(dir), Vector3f.UNIT_Y);

        boolean arrived = checkDistance();
        if (arrived) {
            setStato(Stato.STANDING);

            currentCommand.execute();
        }

    }

    private void setStato(Stato s) {
        currentStato = s;
        switch (s) {
            case STANDING:
            case WAITING:
                control.setCurrentAction("stand");
                break;
            case WALKING:
                control.setCurrentAction("Walk");
                break;
        }

    }

    private boolean checkDistance() {

        Vector3f curr = groupNode.getLocalTranslation();
        float dist = curr.distance(endPos);

        if (dist < 0.2) {

            return true;
        }

        return false;

    }

    public void processStanding() {
    }

    public void addCommand(CommandMove c) {
        commands.add(c);
    }

    public void setResult(Object n) {

    }

    private synchronized int getId() {
        PROG_WALKER++;
        return PROG_WALKER;
    }

    public int getIdWalker() {
        return idWalker;
    }

    public void setDelay(int d) {
        this.delay = d;
        startWait = (new Date()).getTime();
        setStato(Stato.WAITING);
    }

    public Node getNode() {
        return groupNode;
    }

    public void removeMerceTrasportata() {
//        System.out.println("removeMerceTrasportata "+attachmentOfMaterial);
//        for(Spatial sp:groupNode.getChildren())
//            System.out.println(sp);
        if (attachmentOfMaterial != -1) {
            groupNode.detachChildAt(attachmentOfMaterial - 1);
        }
        attachmentOfMaterial=-1;
        prodottoTrasportato = null;
    }

    public int getAttachmentOfMaterial() {
        return attachmentOfMaterial;
    }

    public void setAttachmentOfMaterial(int attachmentOfMaterial) {
        this.attachmentOfMaterial = attachmentOfMaterial;
    }

    public void setProdottoTrasportato(TipoProdotto prodotto) {
        this.prodottoTrasportato = prodotto;
        showMerceTrasportata();
    }

    public TipoProdotto getProdottoTrasportato() {
        return prodottoTrasportato;
    }

    private void showMerceTrasportata() {
        if (prodottoTrasportato == null) {
            return;
        }
        Node n = ModelManager.getModelNodeForTipoProdotto(prodottoTrasportato);
        //  Node n = Pool.getFactory().createItem(prodotto.getModelPath());
//        Geometry geo = Pool.getFactory().createBox();
//        int dd = owner.getNode().attachChild(geo);
        //     owner.setAttachmentOfMaterial(dd);
        if (n != null) {
            int d = getNode().attachChild(n);
            setAttachmentOfMaterial(d);
        }
    }
}

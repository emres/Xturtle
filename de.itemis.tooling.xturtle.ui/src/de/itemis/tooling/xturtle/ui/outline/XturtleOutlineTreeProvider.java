/*
* generated by Xtext
*/
package de.itemis.tooling.xturtle.ui.outline;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.AbstractOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode;

import de.itemis.tooling.xturtle.xturtle.Directive;
import de.itemis.tooling.xturtle.xturtle.DirectiveBlock;
import de.itemis.tooling.xturtle.xturtle.Directives;
import de.itemis.tooling.xturtle.xturtle.Triples;
import de.itemis.tooling.xturtle.xturtle.TurtleDoc;

/**
 * customization of the default outline structure
 * 
 */
public class XturtleOutlineTreeProvider extends DefaultOutlineTreeProvider {
	protected boolean _isLeaf(TurtleDoc doc) {
		return false;
	}

	protected boolean _isLeaf(DirectiveBlock doc) {
		return false;
	}

	protected boolean _isLeaf(org.eclipse.emf.ecore.EObject modelElement) {
		return true;
	}

	protected void _createNode(IOutlineNode parentNode, Triples modelElement) {
		createEObjectNode(parentNode, modelElement, labelProvider.getImage(modelElement.getSubject()), labelProvider.getText(modelElement.getSubject()), true);
	}

	@Override
	protected void _createChildren(DocumentRootNode parentNode,
			EObject modelElement) {
		TurtleDoc doc = (TurtleDoc)modelElement;
		createTriplesNodes(parentNode, doc.getTriples());
		createDirectiveBlocksNodes(parentNode, doc.getDirectiveblock());
	}

	private void createTriplesNodes(IOutlineNode parentNode, List<Triples> triples) {
		if(triples!=null){
			for (Triples triple : triples) {
				createEObjectNode(parentNode, triple, labelProvider.getImage(triple.getSubject()), labelProvider.getText(triple.getSubject()), true);
			}
		}
	}

	private void createDirectiveBlocksNodes(IOutlineNode parentNode, DirectiveBlock block){
		if(block !=null){
			createDirectivesNode(parentNode, block.getDirecives());
		} else{
			return;
		}
		createTriplesNodes(parentNode, block.getTriples());
		createDirectiveBlocksNodes(parentNode, block.getDirectiveblock());
	}

	private void createDirectivesNode(IOutlineNode parentNode,
			Directives direcives) {
		if(direcives!=null && direcives.getDirective().size()>0){
			AbstractOutlineNode newParent = new AbstractOutlineNode(parentNode,null,"Directives",false) {};
			for (Directive directive : direcives.getDirective()) {
				super.createEObjectNode(newParent, directive);
			}
		}
	}
}

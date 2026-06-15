/*
 * tube_connection_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:04 by COMSOL 6.3.0.290. */
public class tube_connection_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Contact_and_Friction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("pipeDiameter", "360[mm]", "\u7ba1\u5185\u5f84");
    model.param().set("pipeThickness", "20[mm]", "\u7ba1\u58c1\u539a\u5ea6");
    model.param().set("pipeOuterDiameter", "pipeDiameter+2*pipeThickness", "\u7ba1\u5916\u5f84");
    model.param()
         .set("pipeLength", "5*sqrt(pipeDiameter/2*pipeThickness)", "\u7ba1\u957f\u5ea6\uff0c\u907f\u514d\u7aef\u90e8\u6548\u5e94");
    model.param().set("flangeThickness", "26[mm]", "\u6cd5\u5170\u539a\u5ea6");
    model.param().set("flangeDiameter", "520[mm]", "\u6cd5\u5170\u5916\u5f84");
    model.param().set("filletRadius", "12[mm]", "\u5706\u89d2\u534a\u5f84\uff0c\u6cd5\u5170\u5230\u7ba1");
    model.param().set("numBolts", "8", "\u87ba\u6813\u6570");
    model.param().set("boltHeadDiameter", "1.5*boltDiameter", "\u87ba\u6813\u5934\u76f4\u5f84");
    model.param().set("boltHeadThickness", "0.6*boltDiameter", "\u87ba\u6813\u5934\u539a\u5ea6");
    model.param().set("boltDiameter", "24[mm]", "\u87ba\u6813\u76f4\u5f84");
    model.param()
         .set("boltHoleClearance", "2[mm]", "\u87ba\u6813\u5b54\u4e0e\u87ba\u6813\u7684\u5f84\u5411\u95f4\u9699");
    model.param().set("boltHoleDiameter", "boltDiameter+boltHoleClearance", "\u87ba\u6813\u5b54\u5f84");
    model.param().set("boltCircle", "470[mm]", "\u87ba\u6813\u5706\u76f4\u5f84");
    model.param().set("washerDiameter", "boltHeadDiameter*1.15", "\u57ab\u5708\u76f4\u5f84");
    model.param().set("washerThickness", "1.5[mm]", "\u57ab\u5708\u539a\u5ea6");
    model.param().set("axialForce", "500[kN]", "\u8f74\u5411\u529b");
    model.param().set("bendingMoment", "40[kN*m]", "\u5f2f\u77e9");
    model.param().set("pressure", "30[bar]", "\u5185\u90e8\u7ba1\u538b\u529b");
    model.param().set("boltPrestressForce", "150[kN]", "\u87ba\u6813\u9884\u5e94\u529b");
    model.param().set("ps", "1", "\u5f39\u7c27\u677e\u5f1b\u53c2\u6570");
    model.param().set("lp", "0", "\u8377\u8f7d\u53c2\u6570");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"(flangeDiameter-pipeDiameter)/2", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("size", "pipeLength", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"pipeDiameter/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"(flangeDiameter-pipeDiameter)/2-pipeThickness", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .setIndex("size", "pipeLength-flangeThickness", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"pipeDiameter/2+pipeThickness", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("pos", "flangeThickness", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point").set("dif1", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "filletRadius");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord1", new String[]{"pipeDiameter/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .setIndex("coord1", "flangeThickness+filletRadius", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord2", new String[]{"pipeDiameter/2+pipeThickness", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .setIndex("coord2", "flangeThickness+filletRadius", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls2", "ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2")
         .setIndex("coord1", "pipeDiameter/2+pipeThickness+filletRadius", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2").setIndex("coord1", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2")
         .setIndex("coord2", "flangeThickness", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2")
         .setIndex("coord2", "pipeDiameter/2+pipeThickness+filletRadius", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls3", "ls2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls3")
         .set("coord1", new String[]{"pipeDiameter/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls3")
         .setIndex("coord2", "flangeThickness+filletRadius", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("csol1").selection("input")
         .set("fil1", "ls1", "ls2", "ls3");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", 180);
    model.component("comp1").geom("geom1").run("rev1");
    model.geom()
         .load(new String[]{"part1"}, "Structural_Mechanics_Module\\Bolts\\simple_bolt_no_thread_drill.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hdia", "boltHeadDiameter");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hthic", "boltHeadThickness");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "ndia", "boltDiameter");
    model.component("comp1").geom("geom1").feature("pi1")
         .setEntry("inputexpr", "blen", "flangeThickness+washerThickness");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dhrc", "boltHoleClearance/2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dhtc", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dta", 0);
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new String[]{"boltCircle/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("pi1").set("rot", 30);
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("pi1(2)");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "360/numBolts*range(0,1,4)");
    model.component("comp1").geom("geom1").feature("rot1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("rot1").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("rev1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("rot1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("rot2").label("\u87ba\u6813");
    model.component("comp1").geom("geom1").feature("rot2").selection("input").set("pi1(1)");
    model.component("comp1").geom("geom1").feature("rot2").set("rot", "360/numBolts*range(0,1,4)");
    model.component("comp1").geom("geom1").feature("rot2").set("selresult", true);
    model.component("comp1").geom("geom1").run("rot2");
    model.geom()
         .load(new String[]{"part2"}, "Structural_Mechanics_Module\\Bolts\\flat_washer.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "odia", "washerDiameter");
    model.component("comp1").geom("geom1").feature("pi2")
         .setEntry("inputexpr", "idia", "boltDiameter+boltHoleClearance");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "thickness", "washerThickness");
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new String[]{"boltCircle/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("pi2").setIndex("displ", "flangeThickness", 2);
    model.component("comp1").geom("geom1").feature("pi2").set("rot", 30);
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("rot3", "Rotate");
    model.component("comp1").geom("geom1").feature("rot3").label("\u57ab\u5708");
    model.component("comp1").geom("geom1").feature("rot3").selection("input").set("pi2");
    model.component("comp1").geom("geom1").feature("rot3").set("rot", "360/numBolts*range(0,1,4)");
    model.component("comp1").geom("geom1").feature("rot3").set("selresult", true);
    model.component("comp1").geom("geom1").run("rot3");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").named("rot3");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").named("rot2");
    model.component("comp1").geom("geom1").run("pard1");
    model.component("comp1").geom("geom1").create("pard2", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard2").selection("domain").named("rot3");
    model.component("comp1").geom("geom1").run("pard2");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", 0);
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("boxsel1").set("selshow", false);
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").named("boxsel1");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"flangeDiameter+flangeThickness", "1"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .setIndex("size", "(flangeDiameter+flangeThickness)/2", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("pos", new String[]{"-(flangeDiameter+flangeThickness)/2", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").named("rot2");
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("uni3", "Union");
    model.component("comp1").geom("geom1").feature("uni3").selection("input").named("rot3");

    model.title(null);

    model.description("");

    model.label("tube_connection_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

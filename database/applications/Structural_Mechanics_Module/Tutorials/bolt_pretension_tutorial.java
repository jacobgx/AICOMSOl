/*
 * bolt_pretension_tutorial.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:17 by COMSOL 6.3.0.290. */
public class bolt_pretension_tutorial {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("beam", "HermitianBeam", "geom1");

    model.study().create("std1");
    model.study("std1").create("bolt", "BoltPretension");
    model.study("std1").feature("bolt").set("solnum", "auto");
    model.study("std1").feature("bolt").set("notsolnum", "auto");
    model.study("std1").feature("bolt").set("outputmap", new String[]{});
    model.study("std1").feature("bolt").set("ngenAUX", "1");
    model.study("std1").feature("bolt").set("goalngenAUX", "1");
    model.study("std1").feature("bolt").set("ngenAUX", "1");
    model.study("std1").feature("bolt").set("goalngenAUX", "1");
    model.study("std1").feature("bolt").setSolveFor("/physics/solid", true);
    model.study("std1").feature("bolt").setSolveFor("/physics/beam", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("boltDia", "10[mm]", "\u6807\u79f0\u87ba\u6813\u76f4\u5f84");
    model.param().set("holeDia", "boltDia*1.1", "\u87ba\u6813\u5b54\u5f84");
    model.param().set("headDia", "boltDia*1.6", "\u87ba\u6813\u5934\u76f4\u5f84");
    model.param().set("boltSpacing", "55[mm]", "\u87ba\u6813\u95f4\u8ddd");
    model.param().set("thicUp", "10[mm]", "\u4e0a\u677f\u539a\u5ea6");
    model.param().set("thicLow", "20[mm]", "\u4e0b\u677f\u539a\u5ea6");
    model.param().set("threadDepth", "thicLow-4[mm]", "\u87ba\u7eb9\u5b54\u6df1\u5ea6");
    model.param().set("contactWidth", "40[mm]", "\u63a5\u89e6\u5bbd\u5ea6");
    model.param().set("plateLen", "3*boltSpacing+contactWidth", "\u677f\u957f");
    model.param().set("plateWidth", "2*boltSpacing+contactWidth", "\u677f\u5bbd");
    model.param().set("boltForce", "30[kN]", "\u87ba\u6813\u9884\u7d27\u529b");
    model.param().set("numBolt", "5", "\u87ba\u6813\u6570");
    model.param().set("par", "0", "\u89e3\u53c2\u6570");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u957f\u65b9\u4f53\uff1a\u5e95\u90e8");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"plateLen/2", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "plateWidth", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "thicLow", 2);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").feature().duplicate("blk2", "blk1");
    model.component("comp1").geom("geom1").feature("blk2").label("\u957f\u65b9\u4f53\uff1a\u9876\u90e8");
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "thicUp", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "0", "thicLow"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").label("\u957f\u65b9\u4f53\uff1a\u8154");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").feature("blk3")
         .set("size", new String[]{"plateLen/2-contactWidth", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "plateWidth-2*contactWidth", 1);
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "(thicUp+thicLow)/2", 2);
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"0", "contactWidth", "0"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("pos", "(thicUp+thicLow)/4", 2);
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").label("\u5706\u67f1\u4f53\uff1a\u87ba\u6813\u5b54");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "holeDia/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "thicLow+thicUp+2[mm]");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"boltSpacing/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("pos", "contactWidth/2", 1);
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("pos", -1, 2);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("mov1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "boltSpacing");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("mov2", "Move");
    model.component("comp1").geom("geom1").feature("mov2").selection("input").set("mov1");
    model.component("comp1").geom("geom1").feature("mov2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mov2").set("disply", "boltSpacing");
    model.component("comp1").geom("geom1").run("mov2");
    model.geom()
         .load(new String[]{"part1"}, "Structural_Mechanics_Module\\Bolts\\hex_bolt_no_thread.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hgrip", "headDia");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hthic", 6);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "ndia", "boltDia");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "blen", "thicUp+thicLow+10[mm]");
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new String[]{"boltSpacing/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("pi1").setIndex("displ", "contactWidth/2", 1);
    model.component("comp1").geom("geom1").feature("pi1").setIndex("displ", -10, 2);
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_sel5", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_sel1", true);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u87ba\u6813\u548c\u87ba\u6bcd");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_sel1", "csel1");
    model.geom()
         .load(new String[]{"part2"}, "Structural_Mechanics_Module\\Bolts\\hex_nut.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "hgrip", "headDia");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "hdia", "boltDia");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "thickness", 6);
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new String[]{"boltSpacing/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("pi2").setIndex("displ", "contactWidth/2", 1);
    model.component("comp1").geom("geom1").feature("pi2").setIndex("displ", -6, 2);
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepdom", "pi2_sel1", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetodom", "pi2_sel1", "csel1");
    model.geom()
         .load(new String[]{"part3"}, "Structural_Mechanics_Module\\Bolts\\simple_bolt_drill.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "hdia", "headDia");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "hthic", 6);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "ndia", "boltDia");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "sdia", "boltDia-1");
    model.component("comp1").geom("geom1").feature("pi3")
         .setEntry("inputexpr", "blen", "thicUp+threadDepth-(boltDia-1)/(2*tan(50[deg]))");
    model.component("comp1").geom("geom1").feature("pi3")
         .setEntry("inputexpr", "tlen", "threadDepth-(boltDia-1)/(2*tan(50[deg]))");
    model.component("comp1").geom("geom1").feature("pi3").set("displ", new String[]{"boltSpacing/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("pi3").setIndex("displ", "plateWidth-20", 1);
    model.component("comp1").geom("geom1").feature("pi3")
         .setIndex("displ", "thicLow-threadDepth+(boltDia-1)/(2*tan(50[deg]))", 2);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepbnd", "pi3_sel8", true);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepdom", "pi3_sel3.dom", true);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetodom", "pi3_sel3.dom", "csel1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").label("\u590d\u5236\uff1a\u94bb\u5934");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("pi3(2)");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", "boltSpacing");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1")
         .label("\u5dee\u96c6\uff1a\u87ba\u6813\u5b54\u548c\u8154\uff0c\u4e0a");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk2");
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").feature("dif1").selection("input2")
         .set("blk3", "copy1", "cyl1", "mov1", "mov2", "pi3(2)");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").feature().duplicate("dif2", "dif1");
    model.component("comp1").geom("geom1").feature("dif2")
         .label("\u5dee\u96c6\uff1a\u87ba\u6813\u5b54\u548c\u8154\uff0c\u4e0b");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif2").set("keepsubtract", false);
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickoffsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp1").selection("offsetvertex").set("dif1", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .label("\u5706\uff1a\u87ba\u6813\u5934\u7684\u538b\u5370");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "headDia/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .set("pos", new String[]{"3/2*boltSpacing", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").setIndex("pos", "contactWidth/2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1")
         .label("\u590d\u5236\uff1a\u87ba\u6813\u5934\u7684\u538b\u5370");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").set("disply", "boltSpacing");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("copy1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1")
         .label("\u5e76\u96c6\uff1a\u87ba\u6813\u5934\u7684\u538b\u5370");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("dif1", "wp1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").label("\u591a\u8fb9\u5f62\uff1a\u87ba\u6813 2 \u6881");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "3/2*boltSpacing", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "contactWidth/2", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "3/2*boltSpacing", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "contactWidth/2", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 15, 1, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "3/2*boltSpacing", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "contactWidth/2", 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "thicLow+thicUp", 2, 2);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u6881");
    model.component("comp1").geom("geom1").feature("pol1").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("copy2", "Copy");
    model.component("comp1").geom("geom1").feature("copy2").label("\u590d\u5236\uff1a\u87ba\u6813 3 \u6881");
    model.component("comp1").geom("geom1").feature("copy2").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("copy2").set("disply", "boltSpacing");
    model.component("comp1").geom("geom1").feature("copy2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("copy2");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").label("\u591a\u8fb9\u5f62\uff1a\u87ba\u6813 5 \u6881");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "3/2*boltSpacing", 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "plateWidth-contactWidth/2", 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "thicLow-boltDia/2", 0, 2);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "3/2*boltSpacing", 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "plateWidth-contactWidth/2", 1, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "thicLow+5", 1, 2);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "3/2*boltSpacing", 2, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "plateWidth-contactWidth/2", 2, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "thicLow+thicUp", 2, 2);
    model.component("comp1").geom("geom1").feature("pol2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("pol2");

    model.component("comp1").view("view1").set("clippingactive", true);
    model.component("comp1").view("view1").clip().create("plane1", "ClipPlane");
    model.component("comp1").view("view1").clip("plane1")
         .set("orientationaxes", new double[][]{{-1, 0, 0}, {0, -1, 0}, {0, 0, 1}});
    model.component("comp1").view("view1").clip("plane1")
         .set("position", new double[]{51.25, 75, 12.999999999999998});
    model.component("comp1").view("view1").clip("plane1").set("translationamount", 4.9);
    model.component("comp1").view("view1").clip("plane1").setIndex("position", "boltSpacing/2", 0);
    model.component("comp1").view("view1").set("clipshowframes", false);
    model.component("comp1").view("view1").clip().remove("plane1");
    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("beam").selection().named("geom1_csel2_edg");
    model.component("comp1").physics("beam").feature("csd1").label("\u6a2a\u622a\u9762 M10");
    model.component("comp1").physics("beam").feature("csd1").set("SectionType", "CircularSection");
    model.component("comp1").physics("beam").feature("csd1").set("do_circ", "boltDia");
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("OrientationMethod", "vector_beam");
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("vector_beam", new int[]{1, 0, 0});

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.material("mat1").propertyGroup("ElastoplasticModel").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.material("mat1").propertyGroup().create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.material("mat1").label("Structural steel");
    model.material("mat1").set("family", "custom");
    model.material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat1").set("diffuse", "custom");
    model.material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat1").set("ambient", "custom");
    model.material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat1").set("noise", true);
    model.material("mat1").set("fresnel", 0.9);
    model.material("mat1").set("roughness", 0.3);
    model.material("mat1").set("diffusewrap", 0);
    model.material("mat1").set("reflectance", 0);
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.material("mat1").propertyGroup("Enu").func("int2").set("funcnametable", new String[][]{{"int1", "1"}});
    model.material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.material("mat1").propertyGroup("Enu").addInput("temperature");
    model.material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.material("mat1").propertyGroup("ElastoplasticModel").label("Elastoplastic material model");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.material("mat1").propertyGroup("Voce").addInput("temperature");
    model.material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").label("Interpolation 1");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.material("mat1").propertyGroup("Norton").label("Norton");
    model.material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").label("Chaboche viscoplasticity");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").selection().geom("geom1", 1);
    model.component("comp1").material("matlnk2").selection().named("geom1_csel2_edg");

    model.component("comp1").physics("solid").create("rig1", "RigidConnector", 2);
    model.component("comp1").physics("solid").feature("rig1").label("RC \u87ba\u6813 4\uff0c\u87ba\u6813\u5934");
    model.component("comp1").physics("solid").feature("rig1").selection().set(66);
    model.component("comp1").physics("solid").create("rig2", "RigidConnector", 1);
    model.component("comp1").physics("solid").feature("rig2").label("RC \u87ba\u6813 4\uff0c\u87ba\u6bcd");
    model.component("comp1").physics("solid").feature("rig2").selection().set(62, 63, 83, 86);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("solid").feature("dcont1").set("constraintOptions", "nitscheConstraints");
    model.component("comp1").physics("beam").create("brig1", "RigidConnectorBeam", 0);
    model.component("comp1").physics("beam").feature("brig1").label("RC \u87ba\u6813 4\uff0c\u87ba\u6813\u5934");
    model.component("comp1").physics("beam").feature("brig1").selection().set(247);
    model.component("comp1").physics("beam").feature("brig1").set("CoupledRC", "root.comp1.solid.rig1");
    model.component("comp1").physics("beam").create("brig2", "RigidConnectorBeam", 0);
    model.component("comp1").physics("beam").feature("brig2").label("RC \u87ba\u6813 4\uff0c\u87ba\u6bcd");
    model.component("comp1").physics("beam").feature("brig2").selection().set(245);
    model.component("comp1").physics("beam").feature("brig2").set("CoupledRC", "root.comp1.solid.rig2");

    model.component("comp1").multiphysics().create("sbc1", "SolidBeamConnection3D", -1);
    model.component("comp1").multiphysics("sbc1").label("SBC\uff0c\u87ba\u6813 3\uff0c\u87ba\u6813\u5934");
    model.component("comp1").multiphysics("sbc1").set("selectionControl", true);
    model.component("comp1").multiphysics("sbc1").selection("bndPointSolidSelection").set(67);
    model.component("comp1").multiphysics("sbc1").selection("bndPointBeamSelection").set(250);
    model.component("comp1").multiphysics().create("sbc2", "SolidBeamConnection3D", -1);
    model.component("comp1").multiphysics("sbc2").label("SBC\uff0c\u87ba\u6813 3\uff0c\u87ba\u6bcd");
    model.component("comp1").multiphysics("sbc2").set("connectionSettings", "BeamPointsToEdge");
    model.component("comp1").multiphysics("sbc2").selection("bndPointSolidEdgeSelection").set(67, 68, 89, 92);
    model.component("comp1").multiphysics("sbc2").selection("bndPointBeamToEdgeSelection").set(248);
    model.component("comp1").multiphysics().create("sbc3", "SolidBeamConnection3D", -1);
    model.component("comp1").multiphysics("sbc3").label("SBC\uff0c\u87ba\u6813 2\uff0c\u87ba\u6813\u5934");
    model.component("comp1").multiphysics("sbc3").set("selectionControl", true);
    model.component("comp1").multiphysics("sbc3").selection("bndPointSolidSelection").set(52);
    model.component("comp1").multiphysics("sbc3").selection("bndPointBeamSelection").set(253);
    model.component("comp1").multiphysics("sbc3").set("connectedRegion", "DistManual");
    model.component("comp1").multiphysics("sbc3").set("rc", "headDia/2");
    model.component("comp1").multiphysics().create("sbc4", "SolidBeamConnection3D", -1);
    model.component("comp1").multiphysics("sbc4").label("SBC\uff0c\u87ba\u6813 2\uff0c\u87ba\u7eb9");
    model.component("comp1").multiphysics("sbc4").set("selectionControl", true);
    model.component("comp1").multiphysics("sbc4").selection("bndPointSolidSelection").set(34, 35, 43, 46);
    model.component("comp1").multiphysics("sbc4").selection("bndPointBeamSelection").set(251);
    model.component("comp1").multiphysics("sbc4").set("connectedRegion", "ConCriterion");
    model.component("comp1").multiphysics("sbc4").set("i_conn", "Z>thicLow-boltDia/2");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "forceScaling");
    model.func("an1").set("args", "active,time");
    model.func("an1").set("expr", "if(time<active,0.01,if(time<(active+numBolt),0.7,1))");
    model.func("an1").set("fununit", "1");
    model.func("an1").setIndex("argunit", 1, 0);
    model.func("an1").setIndex("argunit", 1, 1);
    model.func().duplicate("an2", "an1");
    model.func("an2").set("funcname", "setPre");
    model.func("an2").set("expr", "time==1 || abs(time-active)<0.001 || abs(time-(active+numBolt))<0.001");

    model.component("comp1").physics("solid").create("pblt1", "BoltPrestress", -1);
    model.component("comp1").physics("solid").feature("pblt1").set("computeTorque", true);
    model.component("comp1").physics("solid").feature("pblt1").set("l", "1.5[mm]");
    model.component("comp1").physics("solid").feature("pblt1").set("boltHead", "hexagonal");
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt1").selection().named("geom1_pi3_sel8");
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt1")
         .set("preTensionType", "PreTensionForce");
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt1")
         .set("F_pre", "forceScaling(1,par)*boltForce");
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt1").set("seqTight", true);
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt1").set("preCond", "setPre(1,par)");
    model.component("comp1").physics("solid").feature("pblt1").create("sblt2", "BoltSelection", 2);
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt2").set("boltLabel", "Bolt_5");
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt2").selection().named("geom1_pi1_sel5");
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt2")
         .set("preTensionType", "PreTensionForce");
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt2")
         .set("F_pre", "forceScaling(5,par)*boltForce");
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt2").set("seqTight", true);
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt2").set("preCond", "setPre(5,par)");
    model.component("comp1").physics("beam").create("pblt1", "BoltPrestress", -1);
    model.component("comp1").physics("beam").feature("pblt1").feature("sblt1").set("boltLabel", "Bolt_2");
    model.component("comp1").physics("beam").feature("pblt1").feature("sblt1").selection().set(252);
    model.component("comp1").physics("beam").feature("pblt1").feature("sblt1")
         .set("preTensionType", "PreTensionForce");
    model.component("comp1").physics("beam").feature("pblt1").feature("sblt1")
         .set("F_pre", "forceScaling(2,par)*boltForce");
    model.component("comp1").physics("beam").feature("pblt1").feature("sblt1").set("seqTight", true);
    model.component("comp1").physics("beam").feature("pblt1").feature("sblt1").set("preCond", "setPre(2,par)");
    model.component("comp1").physics("beam").feature("pblt1").feature().duplicate("sblt2", "sblt1");
    model.component("comp1").physics("beam").feature("pblt1").feature("sblt2").set("boltLabel", "Bolt_3");
    model.component("comp1").physics("beam").feature("pblt1").feature("sblt2").selection().set(249);
    model.component("comp1").physics("beam").feature("pblt1").feature("sblt2")
         .set("F_pre", "forceScaling(3,par)*boltForce");
    model.component("comp1").physics("beam").feature("pblt1").feature("sblt2").set("preCond", "setPre(3,par)");
    model.component("comp1").physics("beam").feature("pblt1").feature().duplicate("sblt3", "sblt2");
    model.component("comp1").physics("beam").feature("pblt1").feature("sblt3").set("boltLabel", "Bolt_4");
    model.component("comp1").physics("beam").feature("pblt1").feature("sblt3").selection().set(246);
    model.component("comp1").physics("beam").feature("pblt1").feature("sblt3")
         .set("F_pre", "forceScaling(4,par)*boltForce");
    model.component("comp1").physics("beam").feature("pblt1").feature("sblt3").set("preCond", "setPre(4,par)");

    model.component("comp1").pair("ap1").manualSelection(true);
    model.component("comp1").pair("ap1").type("Contact");
    model.component("comp1").pair("ap1").searchTol("1e-2");
    model.component("comp1").pair("ap1").mapping("initial");
    model.component("comp1").pair("ap5").manualSelection(true);
    model.component("comp1").pair("ap5").type("Contact");
    model.component("comp1").pair("ap5").mapping("initial");

    model.component("comp1").physics("solid").feature("dcnt1").create("fric1", "Friction", 2);
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("mu_fric", 0.15);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 0);
    model.component("comp1").physics("solid").feature("fix1").selection().set(1);
    model.component("comp1").physics("solid").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("solid").feature("disp1").selection().set(7);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 49);
    model.component("comp1").physics("solid").feature("lemm1").set("geometricNonlinearity", "linear");
    model.component("comp1").physics("beam").feature("emm1").set("ForceLinearStrainRes", true);

    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat2").label("Nylon");
    model.material("mat2").set("family", "custom");
    model.material("mat2")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat2").set("diffuse", "custom");
    model.material("mat2")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.39215686274509803, 0.9803921568627451});
    model.material("mat2").set("ambient", "custom");
    model.material("mat2")
         .set("customambient", new double[]{0.39215686274509803, 0.39215686274509803, 0.7843137254901961});
    model.material("mat2").set("noise", true);
    model.material("mat2").set("lighting", "phong");
    model.material("mat2").set("shininess", 500);
    model.material("mat2").propertyGroup("def").label("Basic");
    model.material("mat2").propertyGroup("def").set("heatcapacity", "1700[J/(kg*K)]");
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"4", "0", "0", "0", "4", "0", "0", "0", "4"});
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"280e-6[1/K]", "0", "0", "0", "280e-6[1/K]", "0", "0", "0", "280e-6[1/K]"});
    model.material("mat2").propertyGroup("def").set("density", "1150[kg/m^3]");
    model.material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.26[W/(m*K)]", "0", "0", "0", "0.26[W/(m*K)]", "0", "0", "0", "0.26[W/(m*K)]"});
    model.material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat2").propertyGroup("Enu").set("E", "2[GPa]");
    model.material("mat2").propertyGroup("Enu").set("nu", "0.4");
    model.component("comp1").material().create("matlnk3", "Link");
    model.component("comp1").material("matlnk3").set("link", "mat2");
    model.component("comp1").material("matlnk3").selection().geom("geom1", 2);
    model.component("comp1").material("matlnk3").selection().set(51);

    model.component("comp1").physics("solid").create("tl1", "ThinLayer", 2);
    model.component("comp1").physics("solid").feature("tl1").label("\u57ab\u5708");
    model.component("comp1").physics("solid").feature("tl1").selection().set(51);
    model.component("comp1").physics("solid").feature("tl1").set("lth", "5e-4[m]");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(1, 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(51);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", 5);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").set(84, 85, 90, 97, 102, 107);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().set(12, 13, 16, 19, 21, 22);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("geom1_csel2_edg");

    model.study("std1").feature("bolt").set("useparam", true);
    model.study("std1").feature("bolt").setIndex("pname", "boltDia", 0);
    model.study("std1").feature("bolt").setIndex("plistarr", "", 0);
    model.study("std1").feature("bolt").setIndex("punit", "m", 0);
    model.study("std1").feature("bolt").setIndex("pname", "boltDia", 0);
    model.study("std1").feature("bolt").setIndex("plistarr", "", 0);
    model.study("std1").feature("bolt").setIndex("punit", "m", 0);
    model.study("std1").feature("bolt").setIndex("pname", "par", 0);
    model.study("std1").feature("bolt").setIndex("plistarr", "range(1,1,10)", 0);
    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");
    model.study("std1").feature("bolt").set("plot", true);

    model.sol("sol1").feature("v1").feature("comp1_beam_uLin").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_beam_uLin").set("scaleval", "1e-3");
    model.sol("sol1").feature("v1").feature("comp1_beam_thLin").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_beam_thLin").set("scaleval", 0.01);
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol1").feature("s1").feature("p1").set("pinitstep", 1);
    model.sol("sol1").feature("s1").feature("p1").set("pminstep", 1);
    model.sol("sol1").feature("s1").feature("p1").set("pmaxstep", 1);
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", false);
    model.sol("sol1").feature("s1").feature("fc1").set("plot", true);

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", "1");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().remove("pg1");

    model.study("std1").feature("bolt").set("plotgroup", "Default");

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", "1");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").run();
    model.result("pg1").label("\u87ba\u6813\u5e94\u529b\u548c\u63a5\u89e6\u538b\u529b");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("rangecoloractive", true);
    model.result("pg1").feature("vol1").set("rangecolormax", 500);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").create("sel1", "Selection");
    model.result("pg1").feature("vol1").feature("sel1").selection().named("geom1_csel1_dom");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "solid.Tn");
    model.result("pg1").feature("surf1").set("descr", "\u63a5\u89e6\u538b\u529b");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormax", 30);
    model.result("pg1").feature("surf1").set("colortabletype", "discrete");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg1").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", "beam.mises");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "beam.re");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("inheritplot", "vol1");

    model.component("comp1").view("view1").set("transparency", false);

    model.result("pg1").feature("line1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("line1").feature("def1").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg1").feature("line1").feature("def1").set("descr", "\u4f4d\u79fb\u573a");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u87ba\u6813\u5e73\u9762\u7684\u6a2a\u5411\u5e94\u529b");
    model.result("pg2").create("slc1", "Slice");
    model.result("pg2").feature("slc1").set("expr", "solid.sz");
    model.result("pg2").feature("slc1").set("quickxmethod", "coord");
    model.result("pg2").feature("slc1").set("quickx", "boltSpacing/2 3*boltSpacing/2");
    model.result("pg2").feature("slc1").set("rangecoloractive", true);
    model.result("pg2").feature("slc1").set("rangecolormin", -250);
    model.result("pg2").feature("slc1").set("rangecolormax", 250);
    model.result("pg2").feature("slc1").create("sel1", "Selection");
    model.result("pg2").feature("slc1").feature("sel1").selection().set(1, 2);
    model.result("pg2").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u87ba\u6813\u529b");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"solid.pblt1.sblt1.F_bolt"});
    model.result().evaluationGroup("eg1").feature("gev1").set("descr", new String[]{"\u87ba\u6813\u529b"});
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u87ba\u6813 1", 0);
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("expr", new String[]{"solid.pblt1.sblt1.F_bolt", "beam.pblt1.sblt1.F_bolt"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u87ba\u6813 1", "\u87ba\u6813\u529b"});
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u87ba\u6813 2", 1);
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("expr", new String[]{"solid.pblt1.sblt1.F_bolt", "beam.pblt1.sblt1.F_bolt", "beam.pblt1.sblt2.F_bolt"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u87ba\u6813 1", "\u87ba\u6813 2", "\u87ba\u6813\u529b"});
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u87ba\u6813 3", 2);
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("expr", new String[]{"solid.pblt1.sblt1.F_bolt", "beam.pblt1.sblt1.F_bolt", "beam.pblt1.sblt2.F_bolt", "beam.pblt1.sblt3.F_bolt"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u87ba\u6813 1", "\u87ba\u6813 2", "\u87ba\u6813 3", "\u87ba\u6813\u529b"});
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u87ba\u6813 4", 3);
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("expr", new String[]{"solid.pblt1.sblt1.F_bolt", "beam.pblt1.sblt1.F_bolt", "beam.pblt1.sblt2.F_bolt", "beam.pblt1.sblt3.F_bolt", "solid.pblt1.sblt2.F_bolt"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u87ba\u6813 1", "\u87ba\u6813 2", "\u87ba\u6813 3", "\u87ba\u6813 4", "\u87ba\u6813\u529b"});
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u87ba\u6813 5", 4);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("\u62e7\u7d27\u529b\u77e9");
    model.result().evaluationGroup("eg2").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev1").set("expr", new String[]{"solid.pblt1.sblt1.M_pre"});
    model.result().evaluationGroup("eg2").feature("gev1").set("descr", new String[]{"\u62e7\u7d27\u529b\u77e9"});
    model.result().evaluationGroup("eg2").feature("gev1")
         .set("expr", new String[]{"solid.pblt1.sblt1.M_pre", "solid.pblt1.sblt2.M_pre"});
    model.result().evaluationGroup("eg2").feature("gev1")
         .set("descr", new String[]{"\u62e7\u7d27\u529b\u77e9", "\u62e7\u7d27\u529b\u77e9"});
    model.result().evaluationGroup("eg2").run();

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "4[MPa]*par");
    model.component("comp1").physics("solid").feature("bndl1").selection().set(4, 5, 6, 7, 26, 51, 53, 54, 55, 65);
    model.component("comp1").physics("solid").feature("dcnt1").set("ContactTolType", "Manual");
    model.component("comp1").physics("solid").feature("dcnt1").set("tolcontact", "1[um]");

    model.study("std1").feature("bolt").set("useadvanceddisable", true);
    model.study("std1").feature("bolt").set("disabledphysics", new String[]{"solid/bndl1"});
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/beam", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/sbc1", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/sbc2", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/sbc3", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/sbc4", true);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "boltDia", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "boltDia", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "par", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "0.2 0.5 1", 0);
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initmethod", "sol");
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").feature("comp1_beam_thLin").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_beam_thLin").set("scaleval", 0.01);
    model.sol("sol2").feature("v1").feature("comp1_beam_uLin").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_beam_uLin").set("scaleval", 0.001);
    model.sol("sol2").feature("s1").feature("aDef").set("cachepattern", false);
    model.sol("sol2").feature("s1").feature("fc1").set("dtech", "const");

    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3")
         .label("\u87ba\u6813\u5e94\u529b\u548c\u63a5\u89e6\u538b\u529b\uff0c\u5de5\u4f5c\u8f7d\u8377");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").label("\u8fb9\u754c\u8f7d\u8377 (solid)");
    model.result("pg4").set("showlegends", true);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg4").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").feature("surf1").set("inheritcolor", false);
    model.result("pg4").feature("surf1").set("inheritrange", false);
    model.result("pg4").feature("surf1").set("inherittransparency", false);
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def").set("scale", 1);
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg4").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278);
    model.result("pg4").feature("surf1").create("tran1", "Transparency");
    model.result("pg4").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1")
         .set("expr", new String[]{"solid.bndl1.fax", "solid.bndl1.fay", "solid.bndl1.faz"});
    model.result("pg4").feature("arws1").set("placement", "gausspoints");
    model.result("pg4").feature("arws1").set("arrowbase", "head");
    model.result("pg4").feature("arws1").label("\u8fb9\u754c\u8f7d\u8377 1");
    model.result("pg4").feature("arws1").set("inheritplot", "none");
    model.result("pg4").feature("arws1").create("col", "Color");
    model.result("pg4").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg4").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg4").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg4").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg4").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg4").feature("arws1").feature("col").set("topcolor", "red");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg4").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg4").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg4").feature("arws1").set("color", "red");
    model.result("pg4").feature("arws1").create("def", "Deform");
    model.result("pg4").feature("arws1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("arws1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("arws1").feature("def").set("scale", 1);
    model.result("pg4").feature().move("surf1", 1);
    model.result("pg4").label("\u8fb9\u754c\u8f7d\u8377 (solid)");
    model.result("pg4").run();
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");
    model.result().evaluationGroup("eg3").label("\u87ba\u6813\u526a\u529b\uff08\u5de5\u4f5c\u8f7d\u8377\uff09");
    model.result().evaluationGroup("eg3").set("data", "dset2");
    model.result().evaluationGroup("eg3").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg3").feature("gev1").set("expr", new String[]{"solid.pblt1.sblt1.F_shear"});
    model.result().evaluationGroup("eg3").feature("gev1").set("descr", new String[]{"\u87ba\u6813\u526a\u529b"});
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("descr", "\u87ba\u6813 1", 0);
    model.result().evaluationGroup("eg3").feature("gev1")
         .set("expr", new String[]{"solid.pblt1.sblt1.F_shear", "beam.pblt1.sblt1.F_shear"});
    model.result().evaluationGroup("eg3").feature("gev1")
         .set("descr", new String[]{"\u87ba\u6813 1", "\u87ba\u6813\u526a\u529b"});
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("descr", "\u87ba\u6813 2", 1);
    model.result().evaluationGroup("eg3").feature("gev1")
         .set("expr", new String[]{"solid.pblt1.sblt1.F_shear", "beam.pblt1.sblt1.F_shear", "beam.pblt1.sblt2.F_shear"});
    model.result().evaluationGroup("eg3").feature("gev1")
         .set("descr", new String[]{"\u87ba\u6813 1", "\u87ba\u6813 2", "\u87ba\u6813\u526a\u529b"});
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("descr", "\u87ba\u6813 3", 2);
    model.result().evaluationGroup("eg3").feature("gev1")
         .set("expr", new String[]{"solid.pblt1.sblt1.F_shear", "beam.pblt1.sblt1.F_shear", "beam.pblt1.sblt2.F_shear", "beam.pblt1.sblt3.F_shear"});
    model.result().evaluationGroup("eg3").feature("gev1")
         .set("descr", new String[]{"\u87ba\u6813 1", "\u87ba\u6813 2", "\u87ba\u6813 3", "\u87ba\u6813\u526a\u529b"});
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("descr", "\u87ba\u6813 4", 3);
    model.result().evaluationGroup("eg3").feature("gev1")
         .set("expr", new String[]{"solid.pblt1.sblt1.F_shear", "beam.pblt1.sblt1.F_shear", "beam.pblt1.sblt2.F_shear", "beam.pblt1.sblt3.F_shear", "solid.pblt1.sblt2.F_shear"});
    model.result().evaluationGroup("eg3").feature("gev1")
         .set("descr", new String[]{"\u87ba\u6813 1", "\u87ba\u6813 2", "\u87ba\u6813 3", "\u87ba\u6813 4", "\u87ba\u6813\u526a\u529b"});
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("descr", "\u87ba\u6813 5", 4);
    model.result().evaluationGroup("eg3").run();
    model.result("pg3").run();

    model.title("\u6a21\u62df\u87ba\u6813\u9884\u7d27\u8fc7\u7a0b");

    model
         .description("\u5728\u672c\u6559\u5b66\u6a21\u578b\u4e2d\uff0c\u6211\u4eec\u63a2\u8ba8\u4e86\u51e0\u79cd\u4e0d\u540c\u7684\u9884\u7d27\u87ba\u6813\u5efa\u6a21\u65b9\u6cd5\uff0c\u5176\u4e2d\u4f7f\u7528\u5b9e\u4f53\u548c\u6881\u5355\u5143\u5bf9\u87ba\u6813\u8fdb\u884c\u5efa\u6a21\uff0c\u5e76\u663e\u793a\u4e0d\u540c\u7c7b\u578b\u8868\u5f81\u7684\u87ba\u6813\u4e0e\u5939\u7d27\u96f6\u4ef6\u4e4b\u95f4\u7684\u8026\u5408\u3002\u672c\u4f8b\u4f7f\u7528\u4e0d\u540c\u7684\u8f7d\u8377\u6c34\u5e73\uff0c\u6309\u4e00\u5b9a\u987a\u5e8f\u5bf9\u87ba\u6813\u8fdb\u884c\u9884\u7d27\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("bolt_pretension_tutorial.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}

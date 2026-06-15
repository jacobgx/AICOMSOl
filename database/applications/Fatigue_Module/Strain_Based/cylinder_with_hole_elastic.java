/*
 * cylinder_with_hole_elastic.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:02 by COMSOL 6.3.0.290. */
public class cylinder_with_hole_elastic {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fatigue_Module\\Strain_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 100);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 100);
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 90);
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", 10);
    model.component("comp1").geom("geom1").feature("cyl3").set("h", 220);
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new int[]{0, -110, 50});
    model.component("comp1").geom("geom1").feature("cyl3").set("axistype", "y");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("cyl3");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", 12);
    model.component("comp1").geom("geom1").feature("cyl4").set("h", 220);
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new int[]{0, -110, 50});
    model.component("comp1").geom("geom1").feature("cyl4").set("axistype", "y");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").set("dif2", 1);
    model.component("comp1").geom("geom1").feature("pard1").set("partitionwith", "objects");
    model.component("comp1").geom("geom1").feature("pard1").selection("object").set("cyl4");
    model.component("comp1").geom("geom1").feature("pard1").set("keepobject", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{100, 100, 50});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("blk1", "pard1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 4, 10, 11, 12);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(3);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "-200[MPa]"});

    model.group().create("lg1", "LoadGroup");

    model.component("comp1").physics("solid").feature("bndl1").set("loadGroup", "lg1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"210[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"0"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(5, 7);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", 1);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(2);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useloadcase", true);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 3", 2);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 3", 2);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", 0, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "-1.0", 2, 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 3, 0);
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
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("unit", "MPa");
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").physics().create("ftg", "Fatigue", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ftg", false);

    model.component("comp1").physics("ftg").create("stra1", "StrainBasedModel", 2);
    model.component("comp1").physics("ftg").feature("stra1").selection().set(7);
    model.component("comp1").physics("ftg").feature("stra1").set("fatigueLCFType", "fatigueLCFElastic");
    model.component("comp1").physics("ftg").feature("stra1").set("fatigueInputPhysics", "solid");

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().all();
    model.component("comp1").material("mat2").propertyGroup()
         .create("fatigueStrainCoffinManson", "fatigueStrainCoffinManson", "Coffin-Manson[Fatigue]");
    model.component("comp1").material("mat2").propertyGroup("fatigueStrainCoffinManson")
         .set("epsilonf_CM", new String[]{"0.375"});
    model.component("comp1").material("mat2").propertyGroup("fatigueStrainCoffinManson")
         .set("c_CM", new String[]{"-0.60"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("fatigueStressBasquin", "fatigueStressBasquin", "Basquin[Fatigue]");
    model.component("comp1").material("mat2").propertyGroup("fatigueStressBasquin")
         .set("sigmaf_Basquin", new String[]{"1323[MPa]"});
    model.component("comp1").material("mat2").propertyGroup("fatigueStressBasquin")
         .set("b_Basquin", new String[]{"-0.097"});
    model.component("comp1").material("mat2").propertyGroup("def").set("youngsmodulus", new String[]{"solid.E"});
    model.component("comp1").material("mat2").propertyGroup("def").set("poissonsratio", new String[]{"solid.nu"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("elastoplasticRambergOsgood", "elastoplasticRambergOsgood", "Ramberg-Osgood[Fatigue]");
    model.component("comp1").material("mat2").propertyGroup("elastoplasticRambergOsgood")
         .set("K_ROcyclic", new String[]{"1550[MPa]"});
    model.component("comp1").material("mat2").propertyGroup("elastoplasticRambergOsgood")
         .set("n_ROcyclic", new String[]{"0.16"});

    model.study().create("std2");
    model.study("std2").create("ftge", "Fatigue");
    model.study("std2").feature("ftge").set("ftplistmethod", "manual");
    model.study("std2").feature("ftge").set("solnum", "auto");
    model.study("std2").feature("ftge").set("usesol", "off");
    model.study("std2").feature("ftge").set("outputmap", new String[]{});
    model.study("std2").feature("ftge").setSolveFor("/physics/solid", false);
    model.study("std2").feature("ftge").setSolveFor("/physics/ftg", true);
    model.study("std2").feature("ftge").set("usesol", true);
    model.study("std2").feature("ftge").set("notsolmethod", "sol");
    model.study("std2").feature("ftge").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"ftg.ctf"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf1").set("colortablerev", true);
    model.result("pg2").feature("surf1").set("colortable", "Traffic");
    model.result("pg2").label("\u5931\u6548\u5faa\u73af\u6b21\u6570 (ftg)");
    model.result("pg2").feature("surf1").create("mrkr1", "Marker");
    model.result("pg2").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg2").feature("surf1").feature("mrkr1").set("display", "min");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("mrkr1").set("precision", 2);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("mrkr1").active(false);
    model.result("pg2").feature("surf1").feature("mrkr1").active(true);
    model.result("pg2").run();

    model.title("\u5e26\u5b54\u5706\u67f1\u4f4e\u5468\u75b2\u52b3\u5206\u6790\u4e2d\u7684\u7f3a\u53e3\u8fd1\u4f3c");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u7ebf\u5f39\u6027\u6a21\u578b\u8fdb\u884c\u4f4e\u5468\u75b2\u52b3 (LCF) \u5206\u6790\uff0c\u5373\u4f7f\u5b54\u4e0a\u7684\u5e94\u529b\u8fdc\u9ad8\u4e8e\u5c48\u670d\u6781\u9650\u4e5f\u540c\u6837\u9002\u7528\u3002\u5b54\u7684\u5851\u6027\u901a\u8fc7\u57fa\u4e8e\u5c0f\u7f3a\u53e3\u5e94\u529b\u548c\u5e94\u53d8\u573a\u7684\u8fd1\u4f3c\u65b9\u6cd5\u8fdb\u884c\u5904\u7406\u3002");

    model.label("cylinder_with_hole_elastic.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

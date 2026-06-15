/*
 * cylinder_with_hole_plastic.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:02 by COMSOL 6.3.0.290. */
public class cylinder_with_hole_plastic {

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
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{100, 100, 50});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("blk1", "dif2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl4").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("cyl4").set("h", 40);
    model.component("comp1").geom("geom1").feature("cyl4").set("r", 15);
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new int[]{0, 80, 50});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("int2", "Intersection");
    model.component("comp1").geom("geom1").feature("int2").selection("input").set("cyl4", "int1");
    model.component("comp1").geom("geom1").feature("int2").set("keep", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("cyl4");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mcd1", "MeshControlDomains");
    model.component("comp1").geom("geom1").feature("mcd1").selection("input").set("fin", 2);
    model.component("comp1").geom("geom1").run("mcd1");

    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 6, 7);

    model.param().set("para", "0");
    model.param().descr("para", "\u8f7d\u8377\u5faa\u73af\u63a7\u5236");

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(3);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "-200[MPa]*sin(2*pi*para)"});
    model.component("comp1").physics("solid").feature("lemm1").create("plsty1", "Plasticity", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .set("IsotropicHardeningModel", "PerfectlyPlastic");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .set("KinematicHardeningModel", "LinearKinematicHardening");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"210[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"0"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic_material_model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmags", new String[]{"380[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", new String[]{"75[GPa]"});

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").clearMesh();
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 6);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 15);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").feature().move("ftet1", 3);
    model.component("comp1").mesh("mesh1").feature().move("ftet1", 4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.025,1)", 0);
    model.study("std1").label("\u7814\u7a76 1\uff08\u7b2c\u4e00\u6b21\u8f7d\u8377\u5faa\u73af\uff09");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 41, 0);
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
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", 2000);
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").feature("vol1").feature("def").set("scaleactive", false);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u5851\u6027\u5e94\u53d8");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(6);
    model.result("pg2").feature("ptgr1").set("expr", "solid.epeGp");
    model.result("pg2").feature("ptgr1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u5e94\u529b vs. \u5e94\u53d8");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").selection().set(6);
    model.result("pg3").feature("ptgr1").set("expr", "solid.sGpzz");
    model.result("pg3").feature("ptgr1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0czz \u5206\u91cf");
    model.result("pg3").feature("ptgr1").set("xdataexpr", "solid.eZZ");
    model.result("pg3").feature("ptgr1").set("xdatadescr", "\u5e94\u53d8\u5f20\u91cf\uff0cZZ \u5206\u91cf");
    model.result("pg3").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(1,0.025,2)", 0);
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initmethod", "sol");
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").label("\u7814\u7a76 2\uff08\u7a33\u6001\u8f7d\u8377\u5faa\u73af\uff09");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").set("data", "dset2");
    model.result("pg2").feature("ptgr2").set("linestyle", "dotted");
    model.result("pg2").feature("ptgr2").set("linewidth", 4);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").set("data", "dset2");
    model.result("pg3").feature("ptgr2").set("linestyle", "dotted");
    model.result("pg3").feature("ptgr2").set("linewidth", 4);
    model.result("pg3").run();

    model.component("comp1").physics().create("ftg", "Fatigue", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ftg", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ftg", false);

    model.component("comp1").physics("ftg").create("stra1", "StrainBasedModel", 2);
    model.component("comp1").physics("ftg").feature("stra1").selection().all();
    model.component("comp1").physics("ftg").feature("stra1").set("fatigueInputPhysics", "solid");
    model.component("comp1").physics("ftg").feature("stra1").set("Q", 16);

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
    model.component("comp1").material("mat2").propertyGroup("def").set("youngsmodulus", new String[]{"210[GPa]"});

    model.study().create("std3");
    model.study("std3").create("ftge", "Fatigue");
    model.study("std3").feature("ftge").set("ftplistmethod", "manual");
    model.study("std3").feature("ftge").set("solnum", "auto");
    model.study("std3").feature("ftge").set("usesol", "off");
    model.study("std3").feature("ftge").set("outputmap", new String[]{});
    model.study("std3").feature("ftge").setSolveFor("/physics/solid", false);
    model.study("std3").feature("ftge").setSolveFor("/physics/ftg", true);
    model.study("std3").feature("ftge").set("usesol", true);
    model.study("std3").feature("ftge").set("notsolmethod", "sol");
    model.study("std3").feature("ftge").set("notstudy", "std2");
    model.study("std3").label("\u7814\u7a76 3\uff08\u75b2\u52b3\uff0cSWT\uff09");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"ftg.ctf"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf1").set("colortablerev", true);
    model.result("pg4").feature("surf1").set("colortable", "Traffic");
    model.result("pg4").label("\u5931\u6548\u5faa\u73af\u6b21\u6570 (ftg)");
    model.result("pg4").feature("surf1").create("mrkr1", "Marker");
    model.result("pg4").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg4").feature("surf1").feature("mrkr1").set("display", "min");
    model.result("pg4").run();
    model.result("pg4").label("\u75b2\u52b3\u5bff\u547d");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u4ee5\u5faa\u73af\u6b21\u6570\u8868\u793a\u7684\u5bff\u547d\u5bf9\u6570");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("mrkr1").set("precision", 2);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("mrkr1").active(false);
    model.result("pg4").feature("surf1").feature("mrkr1").active(true);
    model.result("pg4").run();

    model.title("\u5e26\u5b54\u6c14\u7f38\u7684\u5f39\u5851\u6027\u4f4e\u5468\u75b2\u52b3\u5206\u6790");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u57fa\u4e8e\u5f39\u5851\u6027\u5206\u6790\u8fdb\u884c\u4f4e\u5468\u75b2\u52b3 (LCF) \u5206\u6790\uff0c\u5176\u4e2d\u4f7f\u7528\u4e24\u4e2a\u8f7d\u8377\u5faa\u73af\u4e2d\u7684\u6700\u540e\u4e00\u4e2a\u8fdb\u884c\u75b2\u52b3\u5206\u6790\uff0c\u4ee5\u786e\u4fdd\u72b6\u6001\u8868\u793a\u91cd\u590d\u8f7d\u8377\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("cylinder_with_hole_plastic.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

/*
 * shaft_with_fillet.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:03 by COMSOL 6.3.0.290. */
public class shaft_with_fillet {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fatigue_Module\\Stress_Based");

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
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("x", "0 0 32 32 50 50 0");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("y", "0 5 5 8 8 0 0");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("pointinsketch")
         .set("pol1", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev1").set("origfaces", false);
    model.component("comp1").geom("geom1").feature("rev1").set("axis", new int[]{1, 0});
    model.component("comp1").geom("geom1").run("rev1");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u75b2\u52b3\u8bc4\u4f30\u7684\u9009\u62e9");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection("sel1").add(4, 16);

    model.component("comp1").physics("solid").create("tl1", "ThinLayer", 2);
    model.component("comp1").physics("solid").feature("tl1").selection().named("sel1");
    model.component("comp1").physics("solid").feature("tl1").set("lth", "1e-6[m]");
    model.component("comp1").physics("solid").feature("tl1").set("thicknessApproximation", "membrane");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(21, 22, 23, 24);
    model.component("comp1").physics("solid").create("rig1", "RigidConnector", 2);
    model.component("comp1").physics("solid").feature("rig1").selection().set(1, 3, 5, 7);
    model.component("comp1").physics("solid").feature("rig1").create("rf1", "RigidBodyForce", -1);
    model.component("comp1").physics("solid").feature("rig1").feature("rf1")
         .set("Ft", new String[]{"0", "0", "-1.94[kN]"});

    model.group().create("lg1", "LoadGroup");

    model.component("comp1").physics("solid").feature("rig1").feature("rf1").set("loadGroup", "lg1");
    model.component("comp1").physics("solid").feature("rig1").create("rm1", "RigidBodyMoment", -1);
    model.component("comp1").physics("solid").feature("rig1").feature("rm1")
         .set("Mt", new String[]{"28.7[N*m]", "0", "0"});

    model.group().create("lg2", "LoadGroup");

    model.component("comp1").physics("solid").feature("rig1").feature("rm1").set("loadGroup", "lg2");

    model.group("lg1").label("\u6a2a\u5411\u529b");
    model.group("lg1").paramName("lgF");
    model.group("lg2").label("\u626d\u77e9");
    model.group("lg2").paramName("lgM");

    model.material().create("mat1", "Common", "");
    model.component("comp1").material().create("matlnk1", "Link");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.material("mat1").propertyGroup("Enu").set("E", new String[]{"100[GPa]"});
    model.material("mat1").propertyGroup("Enu").set("nu", new String[]{"0"});
    model.material("mat1").propertyGroup("def").set("density", new String[]{"0"});
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").selection().geom("geom1", 2);
    model.component("comp1").material("matlnk2").selection().named("sel1");

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().set(13, 14, 16, 18);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmax", 0.5);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hgrad", 1.2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useloadcase", true);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u6a2a\u5411\u529b", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 0, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u626d\u77e9", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 1, 1);
    model.study("std1").label("\u7814\u7a76 1\uff08\u57fa\u672c\u8377\u8f7d\u5de5\u51b5\uff09");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 2, 0);
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
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 2, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").label("\u5e94\u529b\uff0c\u8584\u5c42 (solid)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("resolution", "custom");
    model.result("pg2").feature("surf1").set("refine", 2);
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg2").feature("surf1").feature("sel1").selection()
         .set(2, 4, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "solid.sGpxx");
    model.result("pg2").feature("surf1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cxx \u5206\u91cf");
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").set("useloadcase", true);
    model.study("std2").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std2").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std2").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std2").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std2").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 3", 2);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 2, 0);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 2, 1);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 2, 1);
    model.study("std2").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 3", 2);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 2, 0);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 2, 1);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 2, 1);
    model.study("std2").feature("stat").setIndex("loadcase", "\u65e0\u8f7d\u8377", 0);
    model.study("std2").feature("stat").setIndex("loadcase", "+F -M", 1);
    model.study("std2").feature("stat").setIndex("loadgroup", true, 1, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", true, 1, 1);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "-1.0", 1, 1);
    model.study("std2").feature("stat").setIndex("loadcase", "+F +M", 2);
    model.study("std2").feature("stat").setIndex("loadgroup", true, 2, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", true, 2, 1);
    model.study("std2").label("\u7814\u7a76 2\uff08\u7ec4\u5408\u8f7d\u8377\u5de5\u51b5\uff09");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").label("\u5e94\u529b (solid) 1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("vol1").set("threshold", "manual");
    model.result("pg3").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("vol1").set("colortable", "Rainbow");
    model.result("pg3").feature("vol1").set("colortabletrans", "none");
    model.result("pg3").feature("vol1").set("colorscalemode", "linear");
    model.result("pg3").feature("vol1").set("resolution", "custom");
    model.result("pg3").feature("vol1").set("refine", 2);
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").create("def", "Deform");
    model.result("pg3").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").label("\u5e94\u529b\uff0c\u8584\u5c42 (solid) 1");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").set("resolution", "custom");
    model.result("pg4").feature("surf1").set("refine", 2);
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg4").feature("surf1").feature("sel1").selection()
         .set(2, 4, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();

    model.component("comp1").physics().create("ftg", "Fatigue", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ftg", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ftg", false);

    model.component("comp1").physics("ftg").create("stre1", "StressBasedModel", 2);
    model.component("comp1").physics("ftg").feature("stre1").label("Findley");
    model.component("comp1").physics("ftg").feature("stre1").selection().named("sel1");
    model.component("comp1").physics("ftg").feature("stre1").set("fatigueInputPhysics", "solid");
    model.component("comp1").physics("ftg").feature("stre1").set("Q", 16);
    model.component("comp1").physics("ftg").create("stre2", "StressBasedModel", 2);
    model.component("comp1").physics("ftg").feature("stre2").label("Matake");
    model.component("comp1").physics("ftg").feature("stre2").selection().named("sel1");
    model.component("comp1").physics("ftg").feature("stre2").set("fatigueHCFMultiaxModel", "Matake");
    model.component("comp1").physics("ftg").feature("stre2").set("fatigueInputPhysics", "solid");
    model.component("comp1").physics("ftg").feature("stre2").set("Q", 16);
    model.component("comp1").physics("ftg").create("stre3", "StressBasedModel", 2);
    model.component("comp1").physics("ftg").feature("stre3").label("Dang Van");
    model.component("comp1").physics("ftg").feature("stre3").selection().named("sel1");
    model.component("comp1").physics("ftg").feature("stre3").set("fatigueHCFMultiaxModel", "DangVan");
    model.component("comp1").physics("ftg").feature("stre3").set("fatigueInputPhysics", "solid");

    model.material("mat1").propertyGroup()
         .create("fatigueStressFindley", "fatigueStressFindley", "Findley[Fatigue]");
    model.material("mat1").propertyGroup("fatigueStressFindley").set("k_Findley", new String[]{"0.23"});
    model.material("mat1").propertyGroup("fatigueStressFindley").set("f_Findley", new String[]{"440[MPa]"});
    model.material("mat1").propertyGroup().create("fatigueStressMatake", "fatigueStressMatake", "Matake[Fatigue]");
    model.material("mat1").propertyGroup("fatigueStressMatake").set("k_Matake", new String[]{"0.33"});
    model.material("mat1").propertyGroup("fatigueStressMatake").set("f_Matake", new String[]{"467[MPa]"});
    model.material("mat1").propertyGroup()
         .create("fatigueStressDangVan", "fatigueStressDangVan", "Dang_Van[Fatigue]");
    model.material("mat1").propertyGroup("fatigueStressDangVan").set("a_DangVan", new String[]{"0.5"});
    model.material("mat1").propertyGroup("fatigueStressDangVan").set("b_DangVan", new String[]{"467[MPa]"});

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
    model.study("std3").label("\u7814\u7a76 3\uff08\u75b2\u52b3\uff09");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"ftg.fus"});
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("colortable", "Traffic");
    model.result("pg5").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (ftg)");
    model.result("pg5").feature("surf1").create("mrkr1", "Marker");
    model.result("pg5").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg5").feature("surf1").feature("mrkr1").set("display", "max");
    model.result("pg5").run();
    model.result("pg5").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (Findley)");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "ftg.stre1.fus");
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (Matake)");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "ftg.stre2.fus");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (Dang Van)");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("expr", "ftg.stre3.fus");
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50\uff0c\u4e00\u7ef4");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("linewidth", "preference");
    model.result("pg8").feature("lngr1").selection().set(19);
    model.result("pg8").feature("lngr1").set("expr", "ftg.stre1.fus");
    model.result("pg8").feature("lngr1").set("descr", "\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50");
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").feature("lngr1").set("legendmethod", "manual");
    model.result("pg8").feature("lngr1").setIndex("legends", "Findley - \u62c9\u4f38", 0);
    model.result("pg8").feature().duplicate("lngr2", "lngr1");
    model.result("pg8").run();
    model.result("pg8").feature("lngr2").set("expr", "ftg.stre2.fus");
    model.result("pg8").feature("lngr2").setIndex("legends", "Matake - \u62c9\u4f38", 0);
    model.result("pg8").feature().duplicate("lngr3", "lngr2");
    model.result("pg8").run();
    model.result("pg8").feature("lngr3").set("expr", "ftg.stre3.fus");
    model.result("pg8").feature("lngr3").setIndex("legends", "Dang Van - \u62c9\u4f38", 0);
    model.result("pg8").run();
    model.result("pg8").create("lngr4", "LineGraph");
    model.result("pg8").feature("lngr4").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr4").set("linewidth", "preference");
    model.result("pg8").feature("lngr4").selection().set(17);
    model.result("pg8").feature("lngr4").set("expr", "ftg.stre1.fus");
    model.result("pg8").feature("lngr4").set("descr", "\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50");
    model.result("pg8").feature("lngr4").set("linestyle", "dashed");
    model.result("pg8").feature("lngr4").set("linecolor", "cyclereset");
    model.result("pg8").feature("lngr4").set("legend", true);
    model.result("pg8").feature("lngr4").set("legendmethod", "manual");
    model.result("pg8").feature("lngr4").setIndex("legends", "Findley - \u538b\u7f29", 0);
    model.result("pg8").feature().duplicate("lngr5", "lngr4");
    model.result("pg8").run();
    model.result("pg8").feature("lngr5").set("expr", "ftg.stre2.fus");
    model.result("pg8").feature("lngr5").set("linecolor", "cycle");
    model.result("pg8").feature("lngr5").setIndex("legends", "Matake - \u538b\u7f29", 0);
    model.result("pg8").feature().duplicate("lngr6", "lngr5");
    model.result("pg8").run();
    model.result("pg8").feature("lngr6").set("expr", "ftg.stre3.fus");
    model.result("pg8").feature("lngr6").setIndex("legends", "Dang Van - \u538b\u7f29", 0);
    model.result("pg8").run();
    model.result("pg8").run();

    model.title("\u5e26\u5706\u89d2\u7684\u975e\u6bd4\u4f8b\u8f7d\u8377\u8f74\u7684\u75b2\u52b3\u5206\u6790");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5728\u5177\u6709\u975e\u6bd4\u4f8b\u8f7d\u8377\u5386\u7a0b\u7684\u60c5\u51b5\u4e0b\uff08\u7531\u6309\u7167\u4e0d\u540c\u7ec4\u5408\u65b9\u5f0f\u65bd\u52a0\u7684\u6a2a\u5411\u529b\u548c\u626d\u77e9\u4ea7\u751f\uff09\u6267\u884c\u201c\u9ad8\u5468\u75b2\u52b3\u201d(HCF) \u5206\u6790\uff0c\u5e76\u6bd4\u8f83\u4e86\u4e09\u79cd\u4e0d\u540c\u7684\u75b2\u52b3\u6a21\u578b\uff08Findley\u3001Matake \u548c Dang Van\uff09\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("shaft_with_fillet.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

/*
 * cylindrical_test_specimen.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:04 by COMSOL 6.3.0.290. */
public class cylindrical_test_specimen {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fatigue_Module\\Verification_Examples");

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

    model.param().set("Moment", "22.672[N*m]");
    model.param().descr("Moment", "\u626d\u77e9");
    model.param().set("Force", "15708[N]");
    model.param().descr("Force", "\u6cd5\u5411\u529b");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("x", "5 5 0  0 10 10 6.01");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("y", "20 0 0 50 50 30 30");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb1", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 6.01, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 5.25, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 5, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 30, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 25, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 20, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("csol1").selection("input")
         .set("pol1", "qb1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("csol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point").set("csol1", 5);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input").set("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("fil1", "mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev1").set("origfaces", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"210e9"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7800"});

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(3, 4, 25, 27);
    model.component("comp1").physics("solid").create("rig1", "RigidConnector", 2);
    model.component("comp1").physics("solid").feature("rig1").selection().set(11, 12, 47, 48);
    model.component("comp1").physics("solid").feature("rig1").create("rf1", "RigidBodyForce", -1);
    model.component("comp1").physics("solid").feature("rig1").feature("rf1")
         .set("Ft", new String[]{"0", "Force", "0"});

    model.group().create("lg1", "LoadGroup");

    model.component("comp1").physics("solid").feature("rig1").feature("rf1").set("loadGroup", "lg1");
    model.component("comp1").physics("solid").feature("rig1").create("rm1", "RigidBodyMoment", -1);
    model.component("comp1").physics("solid").feature("rig1").feature("rm1")
         .set("Mt", new String[]{"0", "Moment", "0"});

    model.group().create("lg2", "LoadGroup");

    model.component("comp1").physics("solid").feature("rig1").feature("rm1").set("loadGroup", "lg2");

    model.group("lg1").label("\u8f7d\u8377\u7ec4\uff1a\u529b");
    model.group("lg1").paramName("lgF");
    model.group("lg2").label("\u8f7d\u8377\u7ec4\uff1a\u626d\u77e9");
    model.group("lg2").paramName("lgM");

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
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
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 3", 2);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 3", 2);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 4", 3);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 3, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 3, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 3, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 3, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 4", 3);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 3, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 3, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 3, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 3, 1);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "-1.0", 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "-1.0", 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 2, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "-1.0", 2, 1);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 3, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "-1.0", 3, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 3, 1);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 4, 0);
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
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u5e94\u529b");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(20);
    model.result("pg2").feature("ptgr1").set("expr", "solid.SGpYY");
    model.result("pg2").feature("ptgr1")
         .set("descr", "\u7b2c\u4e8c\u7c7b\u76ae\u5965\u62c9-\u57fa\u5c14\u970d\u592b\u5e94\u529b\uff0cYY \u5206\u91cf");
    model.result("pg2").feature("ptgr1").set("unit", "MPa");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "\u6b63\u5e94\u529b", 0);
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").set("expr", "solid.SGpXY");
    model.result("pg2").feature("ptgr2").setIndex("legends", "\u526a\u5207\u5e94\u529b", 0);
    model.result("pg2").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr3").set("expr", "solid.misesGp");
    model.result("pg2").feature("ptgr3").setIndex("legends", "von Mises", 0);
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u5e94\u529b (MPa)");
    model.result("pg2").run();

    model.component("comp1").physics().create("ftg", "Fatigue", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ftg", false);

    model.component("comp1").physics("ftg").label("\u75b2\u52b3 Findley");
    model.component("comp1").physics("ftg").create("stre1", "StressBasedModel", 2);
    model.component("comp1").physics("ftg").feature("stre1").selection().all();
    model.component("comp1").physics("ftg").feature("stre1").set("fatigueInputPhysics", "solid");
    model.component("comp1").physics().create("ftg2", "Fatigue", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ftg2", false);

    model.component("comp1").physics("ftg2").label("\u75b2\u52b3 Matake");
    model.component("comp1").physics("ftg2").create("stre1", "StressBasedModel", 2);
    model.component("comp1").physics("ftg2").feature("stre1").selection().all();
    model.component("comp1").physics("ftg2").feature("stre1").set("fatigueHCFMultiaxModel", "Matake");
    model.component("comp1").physics("ftg2").feature("stre1").set("fatigueInputPhysics", "solid");
    model.component("comp1").physics().create("ftg3", "Fatigue", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ftg3", false);

    model.component("comp1").physics("ftg3").label("\u75b2\u52b3\u6b63\u5e94\u529b");
    model.component("comp1").physics("ftg3").create("stre1", "StressBasedModel", 2);
    model.component("comp1").physics("ftg3").feature("stre1").selection().all();
    model.component("comp1").physics("ftg3").feature("stre1").set("fatigueHCFMultiaxModel", "NormalStress");
    model.component("comp1").physics("ftg3").feature("stre1").set("fatigueInputPhysics", "solid");

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().all();
    model.component("comp1").material("mat2").propertyGroup()
         .create("fatigueStressFindley", "fatigueStressFindley", "Findley[Fatigue]");
    model.component("comp1").material("mat2").propertyGroup("fatigueStressFindley")
         .set("k_Findley", new String[]{"0.20"});
    model.component("comp1").material("mat2").propertyGroup("fatigueStressFindley")
         .set("f_Findley", new String[]{"213[MPa]"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("fatigueStressMatake", "fatigueStressMatake", "Matake[Fatigue]");
    model.component("comp1").material("mat2").propertyGroup("fatigueStressMatake")
         .set("k_Matake", new String[]{"0.27"});
    model.component("comp1").material("mat2").propertyGroup("fatigueStressMatake")
         .set("f_Matake", new String[]{"223[MPa]"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("fatigueStressNormalStress", "fatigueStressNormalStress", "Normal_stress[Fatigue]");
    model.component("comp1").material("mat2").propertyGroup("fatigueStressNormalStress")
         .set("f_NormalStress", new String[]{"576[MPa]"});

    model.study().create("std2");
    model.study("std2").create("ftge", "Fatigue");
    model.study("std2").feature("ftge").set("ftplistmethod", "manual");
    model.study("std2").feature("ftge").set("solnum", "auto");
    model.study("std2").feature("ftge").set("usesol", "off");
    model.study("std2").feature("ftge").set("outputmap", new String[]{});
    model.study("std2").feature("ftge").setSolveFor("/physics/solid", false);
    model.study("std2").feature("ftge").setSolveFor("/physics/ftg", true);
    model.study("std2").feature("ftge").setSolveFor("/physics/ftg2", true);
    model.study("std2").feature("ftge").setSolveFor("/physics/ftg3", true);
    model.study("std2").feature("ftge").set("usesol", true);
    model.study("std2").feature("ftge").set("notsolmethod", "sol");
    model.study("std2").feature("ftge").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"ftg.fus"});
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("colortable", "Traffic");
    model.result("pg3").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (ftg)");
    model.result("pg3").feature("surf1").create("mrkr1", "Marker");
    model.result("pg3").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg3").feature("surf1").feature("mrkr1").set("display", "max");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"ftg2.fus"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("colortable", "Traffic");
    model.result("pg4").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (ftg2)");
    model.result("pg4").feature("surf1").create("mrkr1", "Marker");
    model.result("pg4").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg4").feature("surf1").feature("mrkr1").set("display", "max");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"ftg3.fus"});
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("colortable", "Traffic");
    model.result("pg5").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (ftg3)");
    model.result("pg5").feature("surf1").create("mrkr1", "Marker");
    model.result("pg5").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg5").feature("surf1").feature("mrkr1").set("display", "max");
    model.result("pg3").run();
    model.result("pg3").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (Findley)");
    model.result("pg4").run();
    model.result("pg4").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50 (Matake)");
    model.result("pg5").run();
    model.result("pg5").label("\u75b2\u52b3\u5229\u7528\u7387\u56e0\u5b50\uff08\u6b63\u5e94\u529b\uff09");

    model.title("\u5706\u67f1\u5f62\u8bd5\u6837\u7684\u9ad8\u5468\u75b2\u52b3\u5206\u6790");

    model
         .description("\u4e00\u4e2a\u5706\u67f1\u5f62\u8bd5\u6837\u53d7\u5230\u6cd5\u5411\u8f7d\u8377\u548c\u526a\u5207\u8f7d\u8377\u3002\u57fa\u4e8e\u5e94\u529b\u7684\u4e09\u79cd\u6a21\u578b\uff08Findley\u3001Matake \u548c\u6b63\u5e94\u529b\uff09\u4e0e\u5206\u6790\u503c\u8fdb\u884c\u4e86\u6bd4\u8f83\uff0c\u5e76\u4e14\u8fd9\u4e09\u79cd\u6a21\u578b\u4e5f\u76f8\u4e92\u8fdb\u884c\u4e86\u6bd4\u8f83\uff0c\u672c\u4f8b\u6355\u83b7\u4e86 Matake \u6a21\u578b\u7684\u975e\u5e73\u6ed1\u7279\u6027\u5e76\u8fdb\u884c\u4e86\u8ba8\u8bba\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("cylindrical_test_specimen.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

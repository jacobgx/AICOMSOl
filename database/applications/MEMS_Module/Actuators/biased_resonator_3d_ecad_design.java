/*
 * biased_resonator_3d_ecad_design.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:52 by COMSOL 6.3.0.290. */
public class biased_resonator_3d_ecad_design {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.param().set("t_sub", "0.75[um]");
    model.param().descr("t_sub", "\u57fa\u5e95\u539a\u5ea6");
    model.param().set("t_nitride", "0.15[um]");
    model.param().descr("t_nitride", "\u6c2e\u5316\u7269\u5c42\u539a\u5ea6");
    model.param().set("t_base", "0.3[um]");
    model.param().descr("t_base", "\u591a\u6676\u7845\u57fa\u5c42\u539a\u5ea6");
    model.param().set("t_sl", "0.2[um]");
    model.param().descr("t_sl", "\u727a\u7272\u5c42\u539a\u5ea6");
    model.param().set("t_poly", "1.9[um]");
    model.param().descr("t_poly", "\u591a\u6676\u7845\u5c42\u539a\u5ea6");
    model.param().set("w_box", "38.9[um]");
    model.param().descr("w_box", "\u6846\u5bbd\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").label("\u5bfc\u5165 1 = L1\uff0c\u57fa\u5e95");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "biased_resonator_3d_ecad_design_layout.gds");
    model.component("comp1").geom("geom1").feature("imp1").set("manualelevation", true);
    model.component("comp1").geom("geom1").feature("imp1").setIndex("height", "t_sub", 0);
    model.component("comp1").geom("geom1").feature("imp1").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("imp1").setIndex("importlayer", false, 2);
    model.component("comp1").geom("geom1").feature("imp1").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("imp1").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("imp1").setIndex("importlayer", false, 5);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u57fa\u677f");
    model.component("comp1").geom("geom1").feature("imp1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").feature().duplicate("imp2", "imp1");
    model.component("comp1").geom("geom1").feature("imp2")
         .label("\u5bfc\u5165 2 = L2\uff0c\u6c89\u79ef\u6c2e\u5316\u7269\u5c42");
    model.component("comp1").geom("geom1").feature("imp2").setIndex("importlayer", false, 0);
    model.component("comp1").geom("geom1").feature("imp2").setIndex("height", "t_nitride", 1);
    model.component("comp1").geom("geom1").feature("imp2").setIndex("elevation", "t_sub", 1);
    model.component("comp1").geom("geom1").feature("imp2").setIndex("importlayer", true, 1);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u6c2e\u5316\u7269");
    model.component("comp1").geom("geom1").feature("imp2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("imp2");
    model.component("comp1").geom("geom1").feature().duplicate("imp3", "imp2");
    model.component("comp1").geom("geom1").feature("imp3")
         .label("\u5bfc\u5165 3 = L3\uff0c\u6c89\u79ef\u591a\u6676\u7845\u57fa\u5c42\u5e76\u56fe\u6848\u5316");
    model.component("comp1").geom("geom1").feature("imp3").setIndex("importlayer", false, 1);
    model.component("comp1").geom("geom1").feature("imp3").setIndex("height", "t_base", 2);
    model.component("comp1").geom("geom1").feature("imp3").setIndex("elevation", "t_sub+t_nitride", 2);
    model.component("comp1").geom("geom1").feature("imp3").setIndex("importlayer", true, 2);
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u591a\u6676\u7845\u675f");
    model.component("comp1").geom("geom1").feature("imp3").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("imp3");
    model.component("comp1").geom("geom1").feature().duplicate("imp4", "imp3");
    model.component("comp1").geom("geom1").feature("imp4")
         .label("\u5bfc\u5165 3 = L3\uff0c\u6c89\u79ef\u591a\u6676\u7845\u5e95\u90e8\u7535\u6781\u5c42\u5e76\u5bf9\u5176\u8fdb\u884c\u56fe\u6848\u5316");
    model.component("comp1").geom("geom1").feature("imp4").setIndex("importlayer", false, 2);
    model.component("comp1").geom("geom1").feature("imp4").setIndex("height", "t_base", 3);
    model.component("comp1").geom("geom1").feature("imp4").setIndex("elevation", "t_sub+t_nitride", 3);
    model.component("comp1").geom("geom1").feature("imp4").setIndex("importlayer", true, 3);
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("\u5e95\u7535\u6781");
    model.component("comp1").geom("geom1").feature("imp4").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("imp4");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel1")
         .set("input", new String[]{"imp2_LAYER20", "imp3_LAYER31", "imp4_LAYER32"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").named("unisel1");
    model.component("comp1").geom("geom1").feature("uni1").set("keep", true);
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").set("selresult", true);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("boxsel1").set("input", new String[]{"uni1"});
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", 1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", 35);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", 1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", 11);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmin", "t_sub+t_nitride-0.01");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("off1", "OffsetFaces");
    model.component("comp1").geom("geom1").feature("off1")
         .label("\u504f\u79fb\u9762 1 - \u6c89\u79ef\u727a\u7272\u5c42");
    model.component("comp1").geom("geom1").feature("off1").selection("face").named("boxsel1");
    model.component("comp1").geom("geom1").feature("off1").set("subtractinput", true);
    model.component("comp1").geom("geom1").feature("off1").set("distance", "t_sl");
    model.component("comp1").geom("geom1").feature("off1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("off1").set("selresultshow", "all");
    model.component("comp1").geom("geom1").feature("off1").set("propagatesel", false);
    model.component("comp1").geom("geom1").run("off1");
    model.component("comp1").geom("geom1").feature().duplicate("imp5", "imp4");
    model.component("comp1").geom("geom1").feature("imp5")
         .label("\u5bfc\u5165 5 = L5\uff0c\u727a\u7272\u5c42\u63a9\u819c");
    model.component("comp1").geom("geom1").feature("imp5").setIndex("importlayer", false, 3);
    model.component("comp1").geom("geom1").feature("imp5").setIndex("height", "t_base+t_sl", 4);
    model.component("comp1").geom("geom1").feature("imp5").setIndex("elevation", "t_sub+t_nitride", 4);
    model.component("comp1").geom("geom1").feature("imp5").setIndex("importlayer", true, 4);
    model.component("comp1").geom("geom1").feature("imp5").set("selresult", true);
    model.component("comp1").geom("geom1").feature("imp5").set("selresultshow", "all");
    model.component("comp1").geom("geom1").run("imp5");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"off1", "imp5"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").label("\u4ea4\u96c6 1 = \u727a\u7272\u5c42\u56fe\u6848");
    model.component("comp1").geom("geom1").feature("int1").selection("input").named("unisel2");
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel3")
         .set("input", new String[]{"imp2_LAYER20", "imp3_LAYER31", "imp4_LAYER32", "unisel1", "uni1", "off1", "imp5", "imp5_LAYER40", "unisel2"});
    model.component("comp1").geom("geom1").run("unisel3");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").named("unisel3");
    model.component("comp1").geom("geom1").feature("uni2").set("keep", true);
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("uni2").set("propagatesel", false);
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel2").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("boxsel2").set("input", new String[]{"uni2"});
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmin", "t_sub+t_nitride+t_base+t_sl-0.01");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", "t_sub+t_nitride+t_base+t_sl+0.01");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("\u591a\u6676\u7845\u6c89\u79ef\u9762");
    model.component("comp1").geom("geom1").feature("boxsel2").set("contributeto", "csel5");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel3", "boxsel2");
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmin", "t_sub+t_nitride-0.01");
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmax", "t_sub+t_nitride+0.01");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel4", "boxsel3");
    model.component("comp1").geom("geom1").feature("boxsel4").set("zmin", "t_sub+t_nitride+t_sl-0.01");
    model.component("comp1").geom("geom1").feature("boxsel4").set("zmax", "t_sub+t_nitride+t_base+0.01");
    model.component("comp1").geom("geom1").run("boxsel4");
    model.component("comp1").geom("geom1").create("off2", "OffsetFaces");
    model.component("comp1").geom("geom1").feature("off2")
         .label("\u504f\u79fb\u9762 2 - \u6c89\u79ef\u591a\u6676\u7845\u5c42");
    model.component("comp1").geom("geom1").feature("off2").set("subtractinput", true);
    model.component("comp1").geom("geom1").feature("off2").set("distance", "t_poly");
    model.component("comp1").geom("geom1").feature("off2").set("perpstepedges", true);
    model.component("comp1").geom("geom1").feature("off2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("off2").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").feature("off2").selection("face").named("csel5");
    model.component("comp1").geom("geom1").run("off2");
    model.component("comp1").geom("geom1").feature().duplicate("imp6", "imp5");
    model.component("comp1").geom("geom1").feature("imp6")
         .label("\u5bfc\u5165 6 = L6\uff0c\u591a\u6676\u7845\u5c42\u63a9\u819c");
    model.component("comp1").geom("geom1").feature("imp6").setIndex("importlayer", false, 4);
    model.component("comp1").geom("geom1").feature("imp6").setIndex("height", "t_base+t_sl+t_poly", 5);
    model.component("comp1").geom("geom1").feature("imp6").setIndex("elevation", "t_sub+t_nitride", 5);
    model.component("comp1").geom("geom1").feature("imp6").setIndex("importlayer", true, 5);
    model.component("comp1").geom("geom1").run("imp6");
    model.component("comp1").geom("geom1").create("unisel4", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel4").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel4").set("input", new String[]{"off2", "imp6"});
    model.component("comp1").geom("geom1").run("unisel4");
    model.component("comp1").geom("geom1").create("int2", "Intersection");
    model.component("comp1").geom("geom1").feature("int2")
         .label("\u4ea4\u96c6 2 = \u591a\u6676\u7845\u5c42\u56fe\u6848");
    model.component("comp1").geom("geom1").feature("int2").selection("input").named("unisel4");
    model.component("comp1").geom("geom1").feature("int2").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("int2");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("int1");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input")
         .set("imp1", "imp2", "imp3", "imp4", "int2");
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new String[]{"w_box", "0", "0"});
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Si - Polycrystalline silicon");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.7);
    model.component("comp1").material("mat1").set("roughness", 0.5);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "678[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2320[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"34[W/(m*K)]", "0", "0", "0", "34[W/(m*K)]", "0", "0", "0", "34[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "160e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.22");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Si3N4 - Silicon nitride");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.3e-6[1/K]", "0", "0", "0", "2.3e-6[1/K]", "0", "0", "0", "2.3e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"9.7", "0", "0", "0", "9.7", "0", "0", "0", "9.7"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "3100[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"20[W/(m*K)]", "0", "0", "0", "20[W/(m*K)]", "0", "0", "0", "20[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "250e9[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.23");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").label("SiO2 - Silicon oxide");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.5e-6[1/K]", "0", "0", "0", "0.5e-6[1/K]", "0", "0", "0", "0.5e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "730[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.2", "0", "0", "0", "4.2", "0", "0", "0", "4.2"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "2200[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "70e9[Pa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat2").selection().named("geom1_csel2_dom");
    model.component("comp1").material("mat3").selection().named("geom1_csel1_dom");

    model.component("comp1").physics("solid").selection().set(3, 4, 9, 10);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 3);
    model.component("comp1").physics("solid").feature("fix1").selection().set(4, 10);

    model.component("comp1").mesh("mesh1").autoMeshSize(7);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 3);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("\u632f\u578b (solid)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset1");
    model.result().evaluationGroup("std1EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result().evaluationGroup().create("std1mpf1", "EvaluationGroup");
    model.result().evaluationGroup("std1mpf1").set("data", "dset1");
    model.result().evaluationGroup("std1mpf1").label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 1)");
    model.result().evaluationGroup("std1mpf1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormX", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormY", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormZ", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormX", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormY", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormZ", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLX", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLY", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLZ", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRX", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRY", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRZ", 11);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std1mpf1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").stepNext(0);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").stepNext(0);
    model.result("pg1").run();

    model
         .title("\u504f\u538b\u8c10\u632f\u5668\u7684\u56fa\u6709\u6a21\u6001 - \u6765\u81ea GDS \u6587\u4ef6\u7684\u4e09\u7ef4\u51e0\u4f55");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201cECAD \u5bfc\u5165\u6a21\u5757\u201d\u548c\u201c\u8bbe\u8ba1\u6a21\u5757\u201d\u57fa\u4e8e GDS \u6587\u4ef6\u6765\u6784\u5efa\u4e09\u7ef4\u504f\u538b\u8c10\u632f\u5668\u51e0\u4f55\uff0c\u5176\u4e2d\u6a21\u62df\u534a\u5bfc\u4f53\u548c MEMS \u5236\u9020\u5de5\u827a\uff0c\u4ece\u800c\u66f4\u6709\u6548\u5730\u6784\u5efa\u4e09\u7ef4\u51e0\u4f55\uff0c\u8be5\u64cd\u4f5c\u8fc7\u7a0b\u5bf9\u4e8e\u90a3\u4e9b\u719f\u6089\u534a\u5bfc\u4f53\u548c MEMS \u5236\u9020\u7684\u4eba\u6765\u8bf4\u66f4\u52a0\u76f4\u89c2\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("biased_resonator_3d_ecad_design.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

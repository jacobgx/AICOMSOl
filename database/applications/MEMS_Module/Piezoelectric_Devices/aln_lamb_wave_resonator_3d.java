/*
 * aln_lamb_wave_resonator_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:58 by COMSOL 6.3.0.290. */
public class aln_lamb_wave_resonator_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Piezoelectric_Devices");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").create("ccnp1", "ChargeConservationPiezo");
    model.component("comp1").physics("es").feature("ccnp1").selection().all();
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").create("pzm1", "PiezoelectricMaterialModel");
    model.component("comp1").physics("solid").feature("pzm1").selection().all();

    model.component("comp1").multiphysics().create("pze1", "PiezoelectricEffect", 3);
    model.component("comp1").multiphysics("pze1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("pze1").set("Electrostatics_physics", "es");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("chkeigregion", true);
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("linpsolnum", "auto");
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/es", true);
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std1").feature("eig").setSolveFor("/multiphysics/pze1", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

    model.param().set("tp", "0.4[um]");
    model.param().descr("tp", "\u538b\u7535\u5c42\u539a\u5ea6");
    model.param().set("l", "15[um]");
    model.param().descr("l", "\u53c9\u6307\u957f\u5ea6");
    model.param().set("wf", "0.2[um]");
    model.param().descr("wf", "\u53c9\u6307\u5bbd\u5ea6");
    model.param().set("op", "14.6[um]");
    model.param()
         .descr("op", "\u91cd\u53e0\u90e8\u5206\u7684\u957f\u5ea6\uff0c\u5bf9\u4e8e op \u5c0f\u4e8e l \u7684\u60c5\u51b5");
    model.param().set("dy", "(l-op)/2");
    model.param().descr("dy", "\u7535\u6781\u5206\u79bb");
    model.param().set("n", "11");
    model.param().descr("n", "\u53c9\u6307\u6570\uff0c\u5176\u4e2d (n+1)/4 \u4e3a\u6574\u6570");
    model.param().set("we", "0.2[um]");
    model.param().descr("we", "\u8fb9\u5bbd\u5ea6");
    model.param().set("la", "0.4[um]");
    model.param().descr("la", "\u951a\u7684\u957f\u5ea6");
    model.param().set("Vapp", "1[V]");
    model.param().descr("Vapp", "\u5916\u52a0\u7535\u538b");
    model.param().set("eta0", "2.0e-3");
    model.param().descr("eta0", "\u7535\u6781\u5c42\u635f\u8017\u56e0\u5b50");
    model.param().set("eta1", "2.0e-3");
    model.param().descr("eta1", "\u538b\u7535\u5c42\u635f\u8017\u56e0\u5b50");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"wf", "l"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new String[]{"0", "dy/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("fullsize", new String[]{"(n+1)/4", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"4*wf", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mov1").selection("input").set("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mov1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mov1").set("displx", "2*wf");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mov1").set("disply", "-dy");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mov1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"(n-1/2)*wf+we", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("size", "2*wf", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"0", "(l+dy)/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"2*we", "la"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"(n-1/2)*wf-we", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").setIndex("pos", "(l+dy)/2+2*wf", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input").set("r2", "r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("axis", new int[]{0, -1});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("size", new String[]{"(n+1/2)*wf", "l+dy"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("pos", new String[]{"(n+1/2)*wf/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("par1").selection("input").set("arr1(1,1)");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("par1").selection("tool").set("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("par1").set("keeptool", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("par1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").set("par1", 1);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "tp", 0);
    model.component("comp1").geom("geom1").run("ext1");

    model.component("comp1").selection().create("box1", "Box");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("box1").label("\u4fe1\u53f7");
    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("ymin", "-l/2");
    model.component("comp1").selection("box1").set("zmin", "tp-0.01");
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection().duplicate("box2", "box1");
    model.component("comp1").selection("box2").label("\u63a5\u5730");
    model.component("comp1").selection("box2").set("ymin", Double.NEGATIVE_INFINITY);
    model.component("comp1").selection("box2").set("ymax", "l/2");
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u7535\u6781");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"box1", "box2"});
    model.component("comp1").selection().create("box3", "Box");
    model.component("comp1").selection("box3").label("\u5bf9\u79f0");
    model.component("comp1").selection("box3").set("entitydim", 2);
    model.component("comp1").selection("box3").set("xmax", 0);
    model.component("comp1").selection("box3").set("condition", "inside");
    model.component("comp1").selection().create("box4", "Box");
    model.component("comp1").selection("box4").label("\u9876\u9762");
    model.component("comp1").selection("box4").set("entitydim", 2);
    model.component("comp1").selection("box4").set("zmin", "tp-0.01");
    model.component("comp1").selection("box4").set("condition", "inside");
    model.component("comp1").selection().create("box5", "Box");
    model.component("comp1").selection("box5").label("\u5668\u4ef6");
    model.component("comp1").selection("box5").set("entitydim", 2);
    model.component("comp1").selection("box5").set("ymin", "-((l+dy)/2+2*wf+la)+0.01");
    model.component("comp1").selection("box5").set("ymax", "(l+dy)/2+2*wf+la-0.01");
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u56fa\u5b9a\u7ea6\u675f");
    model.component("comp1").selection("com1").set("entitydim", 2);
    model.component("comp1").selection("com1").set("input", new String[]{"box5"});

    model.component("comp1").physics("es").create("term1", "Terminal", 2);
    model.component("comp1").physics("es").feature("term1").selection().named("box1");
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term1").set("V0", "Vapp");
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().named("box2");
    model.component("comp1").physics("es").create("symp1", "SymmetryPlane", 2);
    model.component("comp1").physics("es").feature("symp1").selection().named("box3");
    model.component("comp1").physics("solid").feature("pzm1").create("mdmp1", "MechanicalDamping", 3);
    model.component("comp1").physics("solid").feature("pzm1").feature("mdmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").create("tl1", "ThinLayer", 2);
    model.component("comp1").physics("solid").feature("tl1").selection().named("uni1");
    model.component("comp1").physics("solid").feature("tl1").set("lth", "80[nm]");
    model.component("comp1").physics("solid").feature("tl1").feature("lemm1").create("dmp1", "Damping", 2);
    model.component("comp1").physics("solid").feature("tl1").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("com1");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("box3");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Pt - Platinum");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.1);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"8.9e6[S/m]", "0", "0", "0", "8.9e6[S/m]", "0", "0", "0", "8.9e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"8.80e-6[1/K]", "0", "0", "0", "8.80e-6[1/K]", "0", "0", "0", "8.80e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "133[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "21450[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"71.6[W/(m*K)]", "0", "0", "0", "71.6[W/(m*K)]", "0", "0", "0", "71.6[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "168e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.38");
    model.component("comp1").material("mat1").selection().geom("geom1", 2);
    model.component("comp1").material("mat1").selection().named("uni1");
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", new String[]{"eta0"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat2").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat2").label("Aluminum Nitride");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.9);
    model.component("comp1").material("mat2").set("roughness", 0.1);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"9", "0", "0", "0", "9", "0", "0", "0", "9"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "3300[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("StrainCharge").label("Strain-charge form");
    model.component("comp1").material("mat2").propertyGroup("StrainCharge")
         .set("sE", new String[]{"2.8987e-12[1/Pa]", "-9.326e-013[1/Pa]", "-5.0038e-013[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-9.326e-013[1/Pa]", "2.8987e-12[1/Pa]", "-5.0038e-013[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-5.0038e-013[1/Pa]", "-5.0038e-013[1/Pa]", "2.8253e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "8E-12[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "8E-12[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "7.6628E-12[1/Pa]"});
    model.component("comp1").material("mat2").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "0[C/N]", "-1.9159e-012[C/N]", "0[C/N]", "0[C/N]", "-1.9159e-012[C/N]", "0[C/N]", "0[C/N]", "4.9597e-012[C/N]", "0[C/N]", 
         "-3.84e-012[C/N]", "0[C/N]", "-3.84e-012[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]"});
    model.component("comp1").material("mat2").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"9.2081", "0", "0", "0", "9.2081", "0", "0", "0", "10.1192"});
    model.component("comp1").material("mat2").propertyGroup("StressCharge").label("Stress-charge form");
    model.component("comp1").material("mat2").propertyGroup("StressCharge")
         .set("cE", new String[]{"4.1e+011[Pa]", "1.49e+011[Pa]", "0.99e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "1.49e+011[Pa]", "4.1e+011[Pa]", "0.99e+011[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0.99e+011[Pa]", "0.99e+011[Pa]", "3.89e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "0[Pa]", "1.25e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "1.25e+011[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "1.305e+011[Pa]"});
    model.component("comp1").material("mat2").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-0.58[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-0.58[C/m^2]", "0[C/m^2]", "0[C/m^2]", "1.55[C/m^2]", "0[C/m^2]", 
         "-0.48[C/m^2]", "0[C/m^2]", "-0.48[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat2").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"9", "0", "0", "0", "9", "0", "0", "0", "9"});
    model.component("comp1").material("mat2").selection().all();
    model.component("comp1").material("mat2").propertyGroup("def").set("lossfactor", new String[]{"eta1"});

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().named("box4");
    model.component("comp1").mesh("mesh1").feature("fq1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmax", 0.1);
    model.component("comp1").mesh("mesh1").run("fq1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run("swe1");

    model.study("std1").label("\u7279\u5f81\u9891\u7387");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 20);
    model.study("std1").feature("eig").set("eigunit", "GHz");
    model.study("std1").feature("eig").set("shift", "8");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("mir1", "Mirror3D");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u632f\u578b");
    model.result("pg1").set("data", "mir1");
    model.result("pg1").set("looplevel", new int[]{3});
    model.result("pg1").set("edges", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "w");
    model.result("pg1").feature("surf1").set("descractive", true);
    model.result("pg1").run();
    model.result("pg1").set("showlegends", false);

    model.view("view3").set("showgrid", false);
    model.view("view3").set("showaxisorientation", false);

    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u632f\u578b\uff0c\u4e2d\u5fc3 XZ \u5e73\u9762");
    model.result("pg2").set("data", "mir1");
    model.result("pg2").set("looplevel", new int[]{3});
    model.result("pg2").create("slc1", "Slice");
    model.result("pg2").feature("slc1").set("expr", "solid.disp");
    model.result("pg2").feature("slc1").set("descractive", true);
    model.result("pg2").feature("slc1").set("quickplane", "zx");
    model.result("pg2").feature("slc1").set("quickynumber", 1);
    model.result("pg2").feature("slc1").set("colortabletype", "discrete");
    model.result("pg2").feature("slc1").set("bandcount", 15);
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/es", true);
    model.study("std2").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/pze1", true);
    model.study("std2").label("\u9891\u57df - 7.95 GHz \u81f3 8.05 GHz");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("freq").set("punit", "GHz");
    model.study("std2").feature("freq").set("plist", "range(7.95,0.005,8.05)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u9891\u57df");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "Log10(Abs(Y11))");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "log10(abs(es.Y11))", 0);
    model.result("pg3").feature("glob1").set("autodescr", false);
    model.result("pg3").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg3").feature("glob1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").feature("gmrk1").set("inclxcoord", true);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("xmin", 7.938);
    model.result("pg3").set("xmax", 8.082);
    model.result("pg3").set("ymin", -4.5);
    model.result("pg3").set("ymax", "-3.0");
    model.result("pg3").run();

    model.title("\u6c2e\u5316\u94dd\u5170\u59c6\u6ce2\u8c10\u632f\u5668 - \u4e09\u7ef4");

    model
         .description("\u5170\u59c6\u6ce2\u8c10\u632f\u5668\u662f\u8bb8\u591a\u5c04\u9891\u5e94\u7528\u4e2d\u7684\u91cd\u8981\u7ec4\u4ef6\u3002\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5bf9\u6c2e\u5316\u94dd\u5170\u59c6\u6ce2\u8c10\u632f\u5668\u8fdb\u884c\u5efa\u6a21\uff0c\u5e76\u6267\u884c\u7279\u5f81\u9891\u7387\u548c\u9891\u7387\u54cd\u5e94\u5206\u6790\u4ee5\u786e\u5b9a\u8be5\u5668\u4ef6\u7684\u7279\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("aln_lamb_wave_resonator_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

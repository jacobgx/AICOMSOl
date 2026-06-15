/*
 * capacitive_micromachined_ultrasonic_transducer_lumped_model.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:02 by COMSOL 6.3.0.290. */
public class capacitive_micromachined_ultrasonic_transducer_lumped_model {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Sensors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").prop("ShapeProperty").set("order_electricpotential", "2m");
    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid");
    model.component("comp1").physics("es").feature("ccns1").selection().all();
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("cir", "Circuit", "geom1");

    model.component("comp1").multiphysics().create("eme1", "ElectromechanicalForces", 3);
    model.component("comp1").multiphysics("eme1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("eme1").set("Electrostatics_physics", "es");

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common("free1").selection().set();
    model.component("comp1").common().create("sym1", "Symmetry");
    model.component("comp1").common("sym1").selection().set();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/cir", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/eme1", true);
    model.study("std1").create("frlin", "Frequencylinearized");
    model.study("std1").feature("frlin").set("outputmap", new String[]{});
    model.study("std1").feature("frlin").set("ngenAUX", "1");
    model.study("std1").feature("frlin").set("goalngenAUX", "1");
    model.study("std1").feature("frlin").set("ngenAUX", "1");
    model.study("std1").feature("frlin").set("goalngenAUX", "1");
    model.study("std1").feature("frlin").setSolveFor("/physics/es", true);
    model.study("std1").feature("frlin").setSolveFor("/physics/solid", true);
    model.study("std1").feature("frlin").setSolveFor("/physics/cir", true);
    model.study("std1").feature("frlin").setSolveFor("/multiphysics/eme1", true);

    model.param().set("l", "63.5[um]");
    model.param().descr("l", "\u5668\u4ef6\u957f\u5ea6");
    model.param().set("l_et", "33[um]");
    model.param().descr("l_et", "\u9876\u90e8\u7535\u6781\u957f\u5ea6");
    model.param().set("l_eb", "35[um]");
    model.param().descr("l_eb", "\u5e95\u90e8\u7535\u6781\u957f\u5ea6");
    model.param().set("t_ox", "1[um]");
    model.param().descr("t_ox", "\u6c27\u5316\u7269\u539a\u5ea6");
    model.param().set("t_m2", "0.64[um]");
    model.param().descr("t_m2", "\u727a\u7272\u91d1\u5c5e\u539a\u5ea6\uff0cM2");
    model.param().set("t_m3", "0.64[um]");
    model.param().descr("t_m3", "\u9876\u90e8\u7535\u6781\u539a\u5ea6\uff0cM3");
    model.param().set("t_m4", "1.28[um]");
    model.param().descr("t_m4", "\u652f\u67b6\u91d1\u5c5e\u539a\u5ea6\uff0cM4");
    model.param().set("t_w", "3.4[um]");
    model.param().descr("t_w", "\u58c1\u539a");
    model.param().set("w_ox", "11.25[um]");
    model.param().descr("w_ox", "\u9876\u90e8\u7535\u6781\u5468\u56f4\u6c27\u5316\u7269\u7684\u5bbd\u5ea6");
    model.param().set("l_v43", "12[um]");
    model.param().descr("l_v43", "\u901a\u5b54 4-3 \u957f\u5ea6");
    model.param().set("l_m4", "15[um]");
    model.param().descr("l_m4", "\u652f\u67b6\u91d1\u5c5e\u957f\u5ea6");
    model.param().set("w_b", "3.6[um]");
    model.param().descr("w_b", "\u652f\u6491\u6881\u5bbd\u5ea6");
    model.param().set("t_np", "1[um]");
    model.param().descr("t_np", "\u6c2e\u5316\u7269\u949d\u5316\u5c42\u539a\u5ea6");
    model.param().set("p_max", "1[MPa]");
    model.param().descr("p_max", "\u6700\u5927\u538b\u529b");
    model.param().set("R_load", "1[Gohm]");
    model.param().descr("R_load", "\u8d1f\u8f7d\u7535\u963b");
    model.param().set("V_a", "5[V]");
    model.param().descr("V_a", "\u5916\u52a0\u7535\u538b");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").label("\u5668\u4ef6");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", "l/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq2", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").label("\u6d3b\u585e");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("size", "l_eb/2+w_ox");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq3", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq3").label("\u5185\u58c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq3").set("size", "l/2-t_w/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq4", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq4").label("\u5e95\u90e8\u7535\u6781");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq4").set("size", "l_eb/2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").label("\u6c27\u5316\u7269 1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "t_ox", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").label("\u91d1\u5c5e 2");
    model.component("comp1").geom("geom1").feature("ext2").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").set("ext1", 8, 16, 4, 12);
    model.component("comp1").geom("geom1").feature("ext2").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "t_m2", 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").label("\u6c27\u5316\u7269 2");
    model.component("comp1").geom("geom1").feature("ext3").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext3").selection("inputface").set("ext2", 8, 16, 4, 12);
    model.component("comp1").geom("geom1").feature("ext3").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "t_ox", 0);
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").label("\u91d1\u5c5e 3");
    model.component("comp1").geom("geom1").feature("ext4").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext4").selection("inputface").set("ext3", 8, 16, 4, 12);
    model.component("comp1").geom("geom1").feature("ext4").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext4").setIndex("distance", "t_m3", 0);
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").feature().create("ext5", "Extrude");
    model.component("comp1").geom("geom1").feature("ext5").label("\u6c27\u5316\u7269 3");
    model.component("comp1").geom("geom1").feature("ext5").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext5").selection("inputface").set("ext4", 8, 16, 4, 12);
    model.component("comp1").geom("geom1").feature("ext5").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext5").setIndex("distance", "t_ox", 0);
    model.component("comp1").geom("geom1").run("ext5");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", "t_ox+t_m2+t_ox+t_m3+t_ox");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("sq1")
         .label("\u652f\u6491\u7269\uff0c\u4e2d\u5fc3");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("sq1").set("size", "l_m4/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").label("\u652f\u6491\u7269\uff0cx");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"l/2-l_m4/2", "w_b/2"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("pos", new String[]{"l_m4/2", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2").label("\u652f\u6491\u7269\uff0cy");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2")
         .set("size", new String[]{"w_b/2", "l/2-l_m4/2"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2")
         .set("pos", new String[]{"0", "l_m4/2"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("sq2", "Square");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("sq2")
         .label("\u5668\u4ef6\uff0c\u4e0a\u90e8");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("sq2").set("size", "l/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("sq2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("sq3", "Square");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("sq3")
         .label("\u5185\u58c1\uff0c\u4e0a\u90e8");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("sq3").set("size", "l/2-t_w/2");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext6", "Extrude");
    model.component("comp1").geom("geom1").feature("ext6").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("ext6").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("ext6").label("\u91d1\u5c5e 4");
    model.component("comp1").geom("geom1").feature("ext6").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext6").setIndex("distance", "t_m4", 0);
    model.component("comp1").geom("geom1").run("ext6");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u6c2e\u5316\u7269");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"l/2", "l/2", "t_np"});
    model.component("comp1").geom("geom1").feature("blk1")
         .set("pos", new String[]{"0", "0", "t_ox+t_m2+t_ox+t_m3+t_ox+t_m4"});
    model.component("comp1").geom("geom1").feature("blk1").set("selresult", true);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7d2f\u79ef\u9009\u62e9 1");
    model.component("comp1").geom("geom1").feature("blk1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("blk1").set("color", "custom");
    model.component("comp1").geom("geom1").feature("blk1")
         .set("customcolor", new double[]{1, 1, 0.6078431606292725});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").label("\u901a\u5b54");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"l_v43/2", "l_v43/2", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "t_ox", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "0", "t_ox+t_m2+t_ox+t_m3"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").label("\u9876\u90e8\u7535\u6781");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"l_et/2", "l_et/2", "1"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "t_m3", 2);
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"0", "0", "t_ox+t_m2+t_ox"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u95f4\u9699");
    model.component("comp1").selection("sel1").set(2, 12, 17, 18, 19, 20, 27);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u6c27\u5316\u7269");
    model.component("comp1").selection("sel2").set(1, 3, 8, 10, 11, 13, 14, 15, 16, 21, 22, 23, 24, 25, 28);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u7535\u6781");
    model.component("comp1").selection("sel3").set(4, 5, 6, 9, 26, 29, 30);
    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u8fb9\uff0c\u4e2d\u5fc3");
    model.component("comp1").selection("box1").set("entitydim", 1);
    model.component("comp1").selection("box1").set("xmax", 0);
    model.component("comp1").selection("box1").set("ymax", 0);
    model.component("comp1").selection("box1").set("condition", "inside");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().set(12);
    model.component("comp1").cpl().create("minop1", "Minimum");
    model.component("comp1").cpl("minop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("minop1").selection().set(12);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Al - Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"35.5e6[S/m]", "0", "0", "0", "35.5e6[S/m]", "0", "0", "0", "35.5e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23.1e-6[1/K]", "0", "0", "0", "23.1e-6[1/K]", "0", "0", "0", "23.1e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "904[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"237[W/(m*K)]", "0", "0", "0", "237[W/(m*K)]", "0", "0", "0", "237[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "70.0e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat1").selection().named("sel3");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("SiO2 - Silicon oxide");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.5e-6[1/K]", "0", "0", "0", "0.5e-6[1/K]", "0", "0", "0", "0.5e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "730[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.2", "0", "0", "0", "4.2", "0", "0", "0", "4.2"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2200[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "70e9[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat2").selection().named("sel2");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").label("Si3N4 - Silicon nitride");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.3e-6[1/K]", "0", "0", "0", "2.3e-6[1/K]", "0", "0", "0", "2.3e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"9.7", "0", "0", "0", "9.7", "0", "0", "0", "9.7"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "3100[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"20[W/(m*K)]", "0", "0", "0", "20[W/(m*K)]", "0", "0", "0", "20[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "250e9[Pa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.23");
    model.component("comp1").material("mat3").selection().named("geom1_csel1_dom");

    model.component("comp1").common("free1").selection().named("sel1");
    model.component("comp1").common("sym1").selection().set(4, 5, 38, 54, 57, 60, 63, 117, 128, 130, 132, 134);

    model.component("comp1").physics("es").prop("ShapeProperty").set("order_electricpotential", 2);
    model.component("comp1").physics("es").feature("ccns1").selection()
         .set(1, 3, 7, 8, 10, 11, 13, 14, 15, 16, 21, 22, 23, 24, 25, 28);
    model.component("comp1").physics("es").create("term1", "DomainTerminal", 3);
    model.component("comp1").physics("es").feature("term1").selection().named("sel3");
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Circuit");
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().set(3);
    model.component("comp1").physics("es").create("symp1", "SymmetryPlane", 2);
    model.component("comp1").physics("es").feature("symp1").selection()
         .set(2, 5, 8, 11, 14, 17, 20, 105, 107, 113, 115, 117, 119, 121, 123, 126, 128, 130, 132, 134, 137, 139, 141, 143, 145, 147);
    model.component("comp1").physics("es").create("symp2", "SymmetryPlane", 2);
    model.component("comp1").physics("es").feature("symp2").selection()
         .set(1, 4, 7, 10, 13, 16, 19, 23, 27, 31, 35, 38, 41, 44, 47, 51, 54, 57, 60, 63, 67, 70, 73, 76, 79, 82);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection()
         .set(2, 8, 11, 14, 17, 20, 105, 107, 113, 115, 119, 121, 123, 126, 137, 139, 141, 143, 145, 147);
    model.component("comp1").physics("solid").create("sym2", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym2").selection()
         .set(1, 7, 10, 13, 16, 19, 23, 27, 31, 35, 41, 44, 47, 51, 67, 70, 73, 76, 79, 82);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection()
         .set(3, 37, 53, 69, 86, 87, 88, 89, 90, 91, 92, 103, 152, 153, 154, 155, 156, 157, 158, 159);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(22);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "p_max");
    model.component("comp1").physics("solid").feature("bndl1").set("harmonicPerturbation", true);
    model.component("comp1").physics("cir").create("IvsU1", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("IvsU1").set("V_src", "root.comp1.es.V0_1");
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "R_load");
    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V1").set("value", "V_a");

    model.component("comp1").mesh("mesh1").autoMeshSize(8);

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("frlin").set("punit", "MHz");
    model.study("std1").feature("frlin").set("plist", "range(1.5,0.2,2.5)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u7ec8\u7aef\u7535\u6d41 vs. \u9891\u7387");
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").setIndex("expr", "es.I0_1", 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u6781\u5206\u5e03");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().set(12, 144, 159, 207);
    model.result("pg2").feature("lngr1").set("expr", "z");
    model.result("pg2").feature("lngr1").set("descractive", true);
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").set("xdatadescractive", true);
    model.result("pg2").feature("lngr1").set("differential", true);
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", "solid.disp");
    model.result("pg3").feature("vol1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg3").feature("vol1").set("descractive", true);
    model.result("pg3").feature("vol1").set("colortable", "RainbowLight");
    model.result("pg3").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u4f4d\u79fb\u5747\u5300\u6027\u56e0\u5b50");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "aveop1(w)/minop1(w)", 0);
    model.result().evaluationGroup("eg1").run();

    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("shift", "1[Hz]");
    model.study("std2").feature("eig").set("chkeigregion", true);
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("linpsolnum", "auto");
    model.study("std2").feature("eig").set("solnum", "auto");
    model.study("std2").feature("eig").set("notsolnum", "auto");
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/es", true);
    model.study("std2").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std2").feature("eig").setSolveFor("/physics/cir", true);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/eme1", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 1);
    model.study("std2").feature("eig").set("eigunit", "MHz");
    model.study("std2").feature("eig").set("shift", "7.5");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").selection().geom("geom1", 1);
    model.result("pg4").selection().set(12, 144, 159, 207);
    model.result("pg4").run();
    model.result("pg4").label("\u7279\u5f81\u6a21\u6001");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("looplevel", new int[]{2});
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", "solid.disp");
    model.result("pg4").feature("vol1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg4").feature("vol1").set("descractive", true);
    model.result("pg4").feature("vol1").set("colortable", "RainbowLight");
    model.result("pg4").run();

    model.title("\u7535\u5bb9\u5f0f\u5fae\u673a\u68b0\u8d85\u58f0\u6362\u80fd\u5668");

    model
         .description("\u7535\u5bb9\u5f0f\u5fae\u673a\u68b0\u8d85\u58f0\u6362\u80fd\u5668 (CMUT) \u662f\u4e00\u79cd\u5fae\u578b\u63a5\u6536\u5668\uff0c\u53ef\u4ee5\u5c06\u8d85\u58f0\u6ce2\u8f6c\u6362\u4e3a\u7528\u4e8e\u9ad8\u5206\u8fa8\u7387\u6210\u50cf\u5e94\u7528\u7684\u7535\u4fe1\u53f7\u3002\u6a21\u578b\u5206\u6790\u4e86\u7ecf\u8fc7\u4f18\u5316\u529b-\u4f4d\u79fb\u7279\u6027\u7684 CMUT \u8bbe\u8ba1\uff0c\u4ee5\u63d0\u9ad8\u8f6c\u6362\u6548\u7387\u3002\u6539\u8fdb\u7684\u6838\u5fc3\u662f\u4f4d\u79fb\u5747\u5300\u6027\u56e0\u5b50\uff0c\u53ef\u4ee5\u901a\u8fc7\u201c\u9891\u57df\uff0c\u9884\u5e94\u529b\u201d\u7814\u7a76\u8fdb\u884c\u8ba1\u7b97\u3002\u8fd9\u79cd\u7279\u6b8a\u7684\u8bbe\u8ba1\u53ef\u4ee5\u6539\u8fdb\u57fa\u4e8e\u538b\u7535\u6362\u80fd\u5668\u7684\u6210\u719f\u533b\u5b66\u6210\u50cf\u6280\u672f\uff0c\u6709\u671b\u5728\u5b9e\u73b0\u5c0f\u578b\u5316\u7684\u540c\u65f6\u5177\u6709\u66f4\u9ad8\u7684\u5206\u8fa8\u7387\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("capacitive_micromachined_ultrasonic_transducer.mph");

    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl2")
         .set("forceReferenceArea", new String[]{"0", "0", "-p_max"});
    model.component("comp1").physics("solid").feature("bndl2").selection().set(22);

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/es", true);
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").feature("stat").setSolveFor("/physics/cir", true);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/eme1", true);
    model.study("std3").feature("stat").label("\u7a33\u6001 - \u529b vs. \u4f4d\u79fb");
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat").set("disabledphysics", new String[]{"solid/bndl1"});

    return model;
  }

  public static Model run2(Model model) {
    model.study("std3").feature("stat").set("useadvanceddisable", false);
    model.study("std3").feature("stat").setSolveFor("/physics/es", false);
    model.study("std3").feature("stat").setSolveFor("/physics/cir", false);
    model.study("std3").feature("stat").setSolveFor("/frame/spatial1", false);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/eme1", false);
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "l", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "l", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "p_max", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "range(0,2e5,1e6)", 0);
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").run();
    model.result("pg5").label("\u6709\u6548\u5f39\u7c27\u5e38\u6570");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").set("switchxy", true);
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "abs(minop1(w))", 0);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "p_max*l^2");
    model.result("pg5").feature("glob1").setIndex("descr", "\u6700\u5927\u4f4d\u79fb", 0);
    model.result("pg5").run();

    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("eta_s_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("eta_s", "1e-2");

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").set("plotgroup", "Default");
    model.study("std4").feature("stat").set("outputmap", new String[]{});
    model.study("std4").feature("stat").set("ngenAUX", "1");
    model.study("std4").feature("stat").set("goalngenAUX", "1");
    model.study("std4").feature("stat").set("ngenAUX", "1");
    model.study("std4").feature("stat").set("goalngenAUX", "1");
    model.study("std4").feature("stat").setSolveFor("/physics/es", true);
    model.study("std4").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std4").feature("stat").setSolveFor("/physics/cir", true);
    model.study("std4").feature("stat").setSolveFor("/multiphysics/eme1", true);
    model.study("std4").create("frlin", "Frequencylinearized");
    model.study("std4").feature("frlin").set("plotgroup", "Default");
    model.study("std4").feature("frlin").set("outputmap", new String[]{});
    model.study("std4").feature("frlin").set("ngenAUX", "1");
    model.study("std4").feature("frlin").set("goalngenAUX", "1");
    model.study("std4").feature("frlin").set("ngenAUX", "1");
    model.study("std4").feature("frlin").set("goalngenAUX", "1");
    model.study("std4").feature("frlin").setSolveFor("/physics/es", true);
    model.study("std4").feature("frlin").setSolveFor("/physics/solid", true);
    model.study("std4").feature("frlin").setSolveFor("/physics/cir", true);
    model.study("std4").feature("frlin").setSolveFor("/multiphysics/eme1", true);
    model.study("std4").feature("stat").set("useadvanceddisable", true);
    model.study("std4").feature("stat").set("disabledphysics", new String[]{"solid/bndl2"});
    model.study("std4").feature("frlin").label("\u9891\u57df\u6270\u52a8\uff0cFEM");
    model.study("std4").feature("frlin").set("punit", "MHz");
    model.study("std4").feature("frlin").set("plist", "range(7.2,0.01,7.8)");
    model.study("std4").feature("frlin").set("useparam", true);
    model.study("std4").feature("frlin").setIndex("pname_aux", "l", 0);
    model.study("std4").feature("frlin").setIndex("plistarr_aux", "", 0);
    model.study("std4").feature("frlin").setIndex("punit_aux", "m", 0);
    model.study("std4").feature("frlin").setIndex("pname_aux", "l", 0);
    model.study("std4").feature("frlin").setIndex("plistarr_aux", "", 0);
    model.study("std4").feature("frlin").setIndex("punit_aux", "m", 0);
    model.study("std4").feature("frlin").setIndex("pname_aux", "p_max", 0);
    model.study("std4").feature("frlin").setIndex("plistarr_aux", "0.01[MPa]", 0);
    model.study("std4").feature("frlin").set("useadvanceddisable", true);
    model.study("std4").feature("frlin").set("disabledphysics", new String[]{"solid/bndl2"});
    model.study("std4").setGenPlots(false);
    model.study("std4").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u9891\u7387\u54cd\u5e94");
    model.result("pg6").set("data", "dset5");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "minop1(w)", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "FEM", 0);
    model.result("pg6").feature("glob1").set("evalmethod", "lintotalrms");
    model.result("pg6").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg6").feature("glob1").set("autosolution", false);
    model.result("pg6").run();

    model.param().create("par2");
    model.param("par2").set("keff", "63000[N/m]");
    model.param("par2").descr("keff", "\u6709\u6548\u5f39\u7c27\u5e38\u6570");
    model.param("par2").set("meff", "keff/(2*pi*7.5[MHz])^2");
    model.param("par2").descr("meff", "\u6709\u6548\u8d28\u91cf");
    model.param("par2").set("c", "1e-5[N*s/m]");
    model.param("par2").descr("c", "\u963b\u5c3c\u7cfb\u6570");
    model.param("par2").set("fapp", "p_max*l^2");
    model.param("par2").descr("fapp", "\u4f5c\u7528\u529b");

    model.component("comp1").physics().create("lms", "LumpedMechanicalSystem", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/lms", true);
    model.study("std1").feature("frlin").setSolveFor("/physics/lms", true);
    model.study("std2").feature("eig").setSolveFor("/physics/lms", true);
    model.study("std3").feature("stat").setSolveFor("/physics/lms", true);
    model.study("std4").feature("stat").setSolveFor("/physics/lms", true);
    model.study("std4").feature("frlin").setSolveFor("/physics/lms", true);

    model.component("comp1").physics("lms").create("K1", "Spring", -1);
    model.component("comp1").physics("lms").feature("K1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("lms").feature("K1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("lms").feature("K1").set("k", "keff");
    model.component("comp1").physics("lms").create("C1", "Damper", -1);
    model.component("comp1").physics("lms").feature("C1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("lms").feature("C1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("lms").feature("C1").set("c", "c");
    model.component("comp1").physics("lms").create("M1", "Mass", -1);
    model.component("comp1").physics("lms").feature("M1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("lms").feature("M1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("lms").feature("M1").set("m", "meff");
    model.component("comp1").physics("lms").create("frc1", "ForceNode", -1);
    model.component("comp1").physics("lms").feature("frc1").set("fp1", "fapp");
    model.component("comp1").physics("lms").feature("frc1").setIndex("Connections", 2, 0, 0);

    model.param("par2").set("fapp", "0.01[MPa]*l^2");

    model.study().create("std5");
    model.study("std5").create("freq", "Frequency");
    model.study("std5").feature("freq").setSolveFor("/physics/es", true);
    model.study("std5").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std5").feature("freq").setSolveFor("/physics/cir", true);
    model.study("std5").feature("freq").setSolveFor("/physics/lms", true);
    model.study("std5").feature("freq").setSolveFor("/multiphysics/eme1", true);
    model.study("std5").feature("freq").label("\u9891\u57df\uff0cLMS");
    model.study("std5").feature("freq").set("punit", "MHz");
    model.study("std5").feature("freq").setSolveFor("/physics/es", false);
    model.study("std5").feature("freq").setSolveFor("/physics/solid", false);
    model.study("std5").feature("freq").setSolveFor("/physics/cir", false);
    model.study("std5").feature("freq").setSolveFor("/frame/spatial1", false);
    model.study("std5").feature("freq").setSolveFor("/multiphysics/eme1", false);
    model.study("std5").feature("freq").set("plist", "range(7.2,0.01,7.8)");
    model.study("std5").setGenPlots(false);
    model.study("std5").createAutoSequences("all");

    model.sol("sol7").runAll();

    model.result("pg6").run();
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u4f4d\u79fb (um)");
    model.result("pg6").create("glob2", "Global");
    model.result("pg6").feature("glob2").set("markerpos", "datapoints");
    model.result("pg6").feature("glob2").set("linewidth", "preference");
    model.result("pg6").feature("glob2").label("\u5168\u5c40 2 - LMS");
    model.result("pg6").feature("glob2").set("data", "dset7");
    model.result("pg6").feature("glob2").set("expr", new String[]{"lms.M1.uRMS"});
    model.result("pg6").feature("glob2").set("descr", new String[]{"\u4f4d\u79fb\u5747\u65b9\u6839 (M1)"});
    model.result("pg6").run();
    model.result("pg6").feature("glob2").setIndex("descr", "LMS", 0);
    model.result("pg6").feature("glob2").set("autosolution", false);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("glob1").createTable("tbl1");
    model.result().table("tbl1").label("FEM \u53c2\u8003\u6570\u636e");

    model.study().create("std6");
    model.study("std6").feature().copy("freq", "std5/freq");
    model.study("std6").create("lsqo", "LSQOptimization");
    model.study("std6").feature("lsqo").set("source", "resultTable");
    model.study("std6").feature("lsqo").setEntry("columnType", "col1", "freq");
    model.study("std6").feature("lsqo").setEntry("frequencyUnit", "col1", "MHz");
    model.study("std6").feature("lsqo").setEntry("modelExpression", "col2", "comp1.lms.M1.uRMS");
    model.study("std6").feature("lsqo").setEntry("unit", "col2", "um");
    model.study("std6").feature("lsqo").setEntry("scaleMethod", "col2", "manual");
    model.study("std6").feature("lsqo").setEntry("scaleValue", "col2", "1e-9");
    model.study("std6").feature("lsqo").setIndex("pname", "c", 0);
    model.study("std6").feature("lsqo").setIndex("initval", "1e-5[N*s/m]", 0);
    model.study("std6").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std6").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std6").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std6").feature("lsqo").setIndex("pname", "c", 0);
    model.study("std6").feature("lsqo").setIndex("initval", "1e-5[N*s/m]", 0);
    model.study("std6").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std6").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std6").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std6").feature("lsqo").setIndex("pname", "fapp", 1);
    model.study("std6").feature("lsqo").setIndex("initval", "0.01[MPa]*l^2", 1);
    model.study("std6").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std6").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std6").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std6").feature("lsqo").setIndex("pname", "fapp", 1);
    model.study("std6").feature("lsqo").setIndex("initval", "0.01[MPa]*l^2", 1);
    model.study("std6").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std6").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std6").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std6").feature("lsqo").setIndex("pname", "keff", 2);
    model.study("std6").feature("lsqo").setIndex("initval", "63000[N/m]", 2);
    model.study("std6").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std6").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std6").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std6").feature("lsqo").setIndex("pname", "keff", 2);
    model.study("std6").feature("lsqo").setIndex("initval", "63000[N/m]", 2);
    model.study("std6").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std6").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std6").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std6").feature("lsqo").setIndex("scale", "3e-5[N*s/m]", 0);
    model.study("std6").feature("lsqo").setIndex("pname", "meff", 1);
    model.study("std6").feature("lsqo").setIndex("initval", "2.882e-11[kg]", 1);
    model.study("std6").feature("lsqo").setIndex("scale", "5e-11[kg]", 1);
    model.study("std6").feature("lsqo").setIndex("scale", "1e5[N/m]", 2);
    model.study("std6").feature("lsqo").set("lsqdatamethod", "lsq");
    model.study("std6").showAutoSequences("all");

    model.sol("sol8").feature("st1").set("splitcomplex", true);
    model.sol("sol8").runAll();

    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").label("\u529b\u5927\u5c0f (K1)");
    model.result("pg7").set("data", "dset8");
    model.result("pg7").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "Force");
    model.result("pg7").set("ylabel", "Force");
    model.result("pg7").feature().create("glob1", "Global");
    model.result("pg7").feature("glob1").set("expr", new String[]{"lms.K1.fAmp"});
    model.result("pg7").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg7").feature("glob1").set("linemarker", "cycle");
    model.result("pg7").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg7").feature("glob1").set("data", "parent");
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").label("\u529b\u76f8\u4f4d (K1)");
    model.result("pg8").set("data", "dset8");
    model.result("pg8").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "Phase");
    model.result("pg8").set("ylabel", "Phase");
    model.result("pg8").feature().create("glob1", "Global");
    model.result("pg8").feature("glob1").set("expr", new String[]{"lms.K1.fPhase"});
    model.result("pg8").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg8").feature("glob1").set("linemarker", "cycle");
    model.result("pg8").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg8").feature("glob1").set("data", "parent");
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").label("\u4f4d\u79fb\u5927\u5c0f (K1)");
    model.result("pg9").set("data", "dset8");
    model.result("pg9").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg9").set("titletype", "manual");
    model.result("pg9").set("title", "Displacement");
    model.result("pg9").set("ylabel", "Displacement");
    model.result("pg9").feature().create("glob1", "Global");
    model.result("pg9").feature("glob1").set("expr", new String[]{"lms.K1.uAmp"});
    model.result("pg9").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg9").feature("glob1").set("linemarker", "cycle");
    model.result("pg9").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg9").feature("glob1").set("data", "parent");
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").label("\u4f4d\u79fb\u76f8\u4f4d (K1)");
    model.result("pg10").set("data", "dset8");
    model.result("pg10").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "Phase");
    model.result("pg10").set("ylabel", "Phase");
    model.result("pg10").feature().create("glob1", "Global");
    model.result("pg10").feature("glob1").set("expr", new String[]{"lms.K1.uPhase"});
    model.result("pg10").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg10").feature("glob1").set("linemarker", "cycle");
    model.result("pg10").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg10").feature("glob1").set("data", "parent");
    model.result("pg7").label("\u529b\u5927\u5c0f (lms)");
    model.result("pg7").feature("glob1").set("expr", new String[]{});
    model.result("pg7").feature("glob1").set("descr", new String[]{});
    model.result("pg7").feature("glob1").set("expr", new String[]{"lms.C1.fAmp"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u5927\u5c0f (K1)", "\u963b\u5c3c\u529b\u5927\u5c0f (C1)"});
    model.result("pg7").feature("glob1").set("expr", new String[]{"lms.K1.fAmp", "lms.C1.fAmp"});
    model.result("pg8").label("\u529b\u76f8\u4f4d (lms)");
    model.result("pg8").feature("glob1").set("expr", new String[]{});
    model.result("pg8").feature("glob1").set("descr", new String[]{});
    model.result("pg8").feature("glob1").set("expr", new String[]{"lms.C1.fPhase"});
    model.result("pg8").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u76f8\u4f4d (K1)", "\u963b\u5c3c\u529b\u76f8\u4f4d (C1)"});
    model.result("pg8").feature("glob1").set("expr", new String[]{"lms.K1.fPhase", "lms.C1.fPhase"});
    model.result("pg9").label("\u4f4d\u79fb\u5927\u5c0f (lms)");
    model.result("pg9").feature("glob1").set("expr", new String[]{});
    model.result("pg9").feature("glob1").set("descr", new String[]{});
    model.result("pg9").feature("glob1").set("expr", new String[]{"lms.C1.uAmp"});
    model.result("pg9").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u53d8\u5f62\u5e45\u503c (K1)", "\u8de8\u963b\u5c3c\u5668\u7684\u4f4d\u79fb\u5927\u5c0f (C1)"});
    model.result("pg9").feature("glob1").set("expr", new String[]{"lms.K1.uAmp", "lms.C1.uAmp"});
    model.result("pg10").label("\u4f4d\u79fb\u76f8\u4f4d (lms)");
    model.result("pg10").feature("glob1").set("expr", new String[]{});
    model.result("pg10").feature("glob1").set("descr", new String[]{});
    model.result("pg10").feature("glob1").set("expr", new String[]{"lms.C1.uPhase"});
    model.result("pg10").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u53d8\u5f62\u76f8\u4f4d (K1)", "\u8de8\u963b\u5c3c\u5668\u7684\u4f4d\u79fb\u76f8\u4f4d (C1)"});
    model.result("pg10").feature("glob1").set("expr", new String[]{"lms.K1.uPhase", "lms.C1.uPhase"});
    model.result("pg7").label("\u529b\u5927\u5c0f (lms) 1");
    model.result("pg7").feature("glob1").set("expr", new String[]{});
    model.result("pg7").feature("glob1").set("descr", new String[]{});
    model.result("pg7").feature("glob1").set("expr", new String[]{"lms.M1.fAmp"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u5927\u5c0f (K1)", "\u963b\u5c3c\u529b\u5927\u5c0f (C1)", "\u60ef\u6027\u529b\u5927\u5c0f (M1)"});
    model.result("pg7").feature("glob1").set("expr", new String[]{"lms.K1.fAmp", "lms.C1.fAmp", "lms.M1.fAmp"});
    model.result("pg8").label("\u529b\u76f8\u4f4d (lms) 1");
    model.result("pg8").feature("glob1").set("expr", new String[]{});
    model.result("pg8").feature("glob1").set("descr", new String[]{});
    model.result("pg8").feature("glob1").set("expr", new String[]{"lms.M1.fPhase"});
    model.result("pg8").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u529b\u76f8\u4f4d (K1)", "\u963b\u5c3c\u529b\u76f8\u4f4d (C1)", "\u60ef\u6027\u529b\u76f8\u4f4d (M1)"});
    model.result("pg8").feature("glob1")
         .set("expr", new String[]{"lms.K1.fPhase", "lms.C1.fPhase", "lms.M1.fPhase"});
    model.result("pg9").label("\u4f4d\u79fb\u5927\u5c0f (lms) 1");
    model.result("pg9").feature("glob1").set("expr", new String[]{});
    model.result("pg9").feature("glob1").set("descr", new String[]{});
    model.result("pg9").feature("glob1").set("expr", new String[]{"lms.M1.uAmp"});
    model.result("pg9").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u53d8\u5f62\u5e45\u503c (K1)", "\u8de8\u963b\u5c3c\u5668\u7684\u4f4d\u79fb\u5927\u5c0f (C1)", "\u4f4d\u79fb\u5927\u5c0f (M1)"});
    model.result("pg9").feature("glob1").set("expr", new String[]{"lms.K1.uAmp", "lms.C1.uAmp", "lms.M1.uAmp"});
    model.result("pg10").label("\u4f4d\u79fb\u76f8\u4f4d (lms) 1");
    model.result("pg10").feature("glob1").set("expr", new String[]{});
    model.result("pg10").feature("glob1").set("descr", new String[]{});
    model.result("pg10").feature("glob1").set("expr", new String[]{"lms.M1.uPhase"});
    model.result("pg10").feature("glob1")
         .set("descr", new String[]{"\u5f39\u7c27\u53d8\u5f62\u76f8\u4f4d (K1)", "\u8de8\u963b\u5c3c\u5668\u7684\u4f4d\u79fb\u76f8\u4f4d (C1)", "\u4f4d\u79fb\u76f8\u4f4d (M1)"});
    model.result("pg10").feature("glob1")
         .set("expr", new String[]{"lms.K1.uPhase", "lms.C1.uPhase", "lms.M1.uPhase"});
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").set("data", "dset8");
    model.result("pg11").setIndex("looplevel", 61, 0);
    model.result("pg11").label("\u52a8\u7f51\u683c");
    model.result("pg11").create("mesh1", "Mesh");
    model.result("pg11").feature("mesh1").set("meshdomain", "volume");
    model.result("pg11").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg11").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg11").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg11").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg11").feature("mesh1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
    model.result("pg11").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg11").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg11").feature("mesh1").set("colorrangeunitinterval", false);
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").label("\u53c2\u6570\u4f30\u8ba1");
    model.result("pg12").set("data", "dset8");
    model.result("pg12").set("xlabelactive", true);
    model.result("pg12").set("xlabel", "\u9891\u7387");
    model.result("pg12").set("titletype", "none");
    model.result("pg12").create("glob1", "Global");
    model.result("pg12").feature("glob1").label("FEM (\u6a21\u578b)");
    model.result("pg12").feature("glob1").set("expr", new String[]{"opt.glsobj.col2.model"});
    model.result("pg12").feature("glob1").set("descr", new String[]{"FEM (\u6a21\u578b)"});
    model.result("pg12").feature("glob1").set("xdata", "expr");
    model.result("pg12").feature("glob1").set("xdataexpr", "freq");
    model.result("pg12").create("glob2", "Global");
    model.result("pg12").feature("glob2").label("FEM (\u6570\u636e)");
    model.result("pg12").feature("glob2").set("expr", new String[]{"opt.glsobj.col2.data"});
    model.result("pg12").feature("glob2").set("descr", new String[]{"FEM (\u6570\u636e)"});
    model.result("pg12").feature("glob2").set("xdata", "expr");
    model.result("pg12").feature("glob2").set("xdataexpr", "freq");
    model.result("pg12").feature("glob2").set("linestyle", "none");
    model.result("pg12").feature("glob2").set("linemarker", "point");
    model.result("pg12").feature("glob2").set("markerpos", "datapoints");
    model.result("pg12").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg7").run();
    model.result("pg12").run();

    model.study("std1").feature("stat").setSolveFor("/physics/lms", false);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid/bndl2"});

    model.sol("sol1").createAutoSequence("std1");

    model.study("std1").feature("frlin").setSolveFor("/physics/lms", false);
    model.study("std1").feature("frlin").set("useadvanceddisable", true);
    model.study("std1").feature("frlin").set("disabledphysics", new String[]{"solid/bndl2"});
    model.study("std2").feature("eig").setSolveFor("/physics/es", false);
    model.study("std2").feature("eig").setSolveFor("/physics/cir", false);
    model.study("std2").feature("eig").setSolveFor("/physics/lms", false);
    model.study("std2").feature("eig").setSolveFor("/frame/spatial1", false);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/eme1", false);
    model.study("std2").feature("eig").set("useadvanceddisable", true);
    model.study("std2").feature("eig").set("disabledphysics", new String[]{"solid/bndl2"});
    model.study("std3").feature("stat").setSolveFor("/physics/lms", false);
    model.study("std4").feature("stat").set("useadvanceddisable", false);
    model.study("std4").feature("stat").setSolveFor("/physics/lms", false);
    model.study("std4").feature("frlin").set("useadvanceddisable", false);
    model.study("std4").feature("frlin").setSolveFor("/physics/lms", false);

    model.result("pg12").run();

    model.title("\u7535\u5bb9\u5f0f\u5fae\u673a\u68b0\u8d85\u58f0\u6362\u80fd\u5668\u96c6\u603b\u6a21\u578b");

    model
         .description("\u7535\u5bb9\u5f0f\u5fae\u673a\u68b0\u8d85\u58f0\u6362\u80fd\u5668 (CMUT) \u80fd\u591f\u5c06\u8d85\u58f0\u6ce2\u8f6c\u6362\u4e3a\u7535\u4fe1\u53f7\uff0c\u7528\u4e8e\u9ad8\u5206\u8fa8\u7387\u6210\u50cf\u5e94\u7528\u3002\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u4ece MEMS \u6362\u80fd\u5668\u7684 FEM \u6a21\u578b\u63a8\u5bfc\u51fa\u5176\u96c6\u603b\u6a21\u578b\uff0c\u5176\u4e2d\u5305\u542b CMUT \u7684 FEM \u6a21\u578b\u4ee5\u53ca\u4f7f\u7528\u201c\u96c6\u603b\u673a\u68b0\u7cfb\u7edf\u201d(LMS) \u63a5\u53e3\u521b\u5efa\u7684\u76f8\u5e94\u96c6\u603b\u6a21\u578b\uff0c\u5e76\u901a\u8fc7\u201c\u53c2\u6570\u4f30\u8ba1\u201d\u7814\u7a76\u5f97\u5230 LMS \u6a21\u578b\u7684\u53c2\u6570\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("capacitive_micromachined_ultrasonic_transducer_lumped_model.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}

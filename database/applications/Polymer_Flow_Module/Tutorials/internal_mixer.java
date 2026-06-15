/*
 * internal_mixer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:09 by COMSOL 6.3.0.290. */
public class internal_mixer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Polymer_Flow_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("rotationalVelocityExpression", "generalRevolutionsPerTime");
    model.component("comp1").common("rot1").selection().all();

    model.study().create("std1");
    model.study("std1").create("frrot", "FrozenRotor");
    model.study("std1").feature("frrot").set("ftplistmethod", "manual");
    model.study("std1").feature("frrot").set("solnum", "auto");
    model.study("std1").feature("frrot").set("notsolnum", "auto");
    model.study("std1").feature("frrot").set("outputmap", new String[]{});
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").setSolveFor("/physics/spf", true);
    model.study("std1").feature("frrot").setSolveFor("/physics/ht", true);
    model.study("std1").feature("frrot").setSolveFor("/multiphysics/nitf1", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "internal_mixer.mphbin");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("cm1", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("cm1").selection("ent").set("imp1(3)", 4, 6, 10, 12);
    model.component("comp1").geom("geom1").run("cm1");
    model.component("comp1").geom("geom1").feature().duplicate("cm2", "cm1");
    model.component("comp1").geom("geom1").feature("cm2").selection("ent").clear("imp1(3)");
    model.component("comp1").geom("geom1").feature("cm2").selection("ent").set("imp1(3)", 22, 24, 28, 30);
    model.component("comp1").geom("geom1").run("cm2");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection("sel1").set("angletol", 50);
    model.component("comp1").selection("sel1")
         .add(19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 61, 62, 63, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 116, 117, 118);

    model.component("comp1").common("rot1").selection().set(2);
    model.component("comp1").common("rot1").set("revolutionsPerTime", "-1/3");
    model.component("comp1").common("rot1")
         .set("rotationAxisBasePoint", new String[]{"geom1.cm1.x", "geom1.cm1.y", "geom1.cm1.z"});
    model.component("comp1").common().duplicate("rot2", "rot1");
    model.component("comp1").common("rot2").selection().set(3);
    model.component("comp1").common("rot2").set("revolutionsPerTime", "1/3");
    model.component("comp1").common("rot2")
         .set("rotationAxisBasePoint", new String[]{"geom1.cm2.x", "geom1.cm2.y", "geom1.cm2.z"});

    model.component("comp1").physics("spf").feature("fp1").set("Constitutiverelation", "InelasticNonNewtonian");
    model.component("comp1").physics("spf").feature("fp1").set("nonNewtonianModels", "Carreau");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Carreau", "Carreau", "Non-Newtonian_Carreau_model");
    model.component("comp1").material("mat1").propertyGroup("Carreau").set("mu0", new String[]{"1130"});
    model.component("comp1").material("mat1").propertyGroup("Carreau").set("mu_inf", new String[]{"50"});
    model.component("comp1").material("mat1").propertyGroup("Carreau").set("lam_car", new String[]{"0.95"});
    model.component("comp1").material("mat1").propertyGroup("Carreau").set("n_car", new String[]{"0.85"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"250"});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"0.2"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"760"});

    model.component("comp1").physics("spf").feature("fp1").set("thermalFunction", "shiftExp");
    model.component("comp1").physics("spf").feature("fp1").set("T0", 453);
    model.component("comp1").physics("spf").feature("fp1").set("be", 0.02);
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(17);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", 313);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().set(8);
    model.component("comp1").physics("ht").feature("hf1").selection().all();
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", 40);
    model.component("comp1").physics("ht").feature("hf1").set("Text", 313);
    model.component("comp1").physics("ht").create("ins2", "ThermalInsulation", 2);
    model.component("comp1").physics("ht").feature("ins2").selection().named("sel1");

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blstretch", 1.15);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhminfact", 1.5);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view2").set("rendermesh", true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(1, 2, 3, 4, 8, 10, 11, 12, 13, 17, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 116, 117, 118);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "T");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u58c1\u6e29");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg4").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118);
    model.result("pg4").feature().create("arwv1", "ArrowVolume");
    model.result("pg4").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg4").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg4").feature("arwv1").set("solutionparams", "parent");
    model.result("pg4").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg4").feature("arwv1").set("xnumber", 30);
    model.result("pg4").feature("arwv1").set("ynumber", 30);
    model.result("pg4").feature("arwv1").set("znumber", 30);
    model.result("pg4").feature("arwv1").set("arrowtype", "cone");
    model.result("pg4").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg4").feature("arwv1").set("data", "parent");
    model.result("pg4").feature("arwv1").feature().create("col1", "Color");
    model.result("pg4").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg4").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg4").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("slc1").set("quickplane", "xy");
    model.result("pg1").feature("slc1").set("quickznumber", 3);
    model.result("pg1").feature("slc1").set("colortable", "Tectocoris");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("xnumber", 60);
    model.result("pg1").feature("arwv1").set("ynumber", 40);
    model.result("pg1").feature("arwv1").set("znumber", 3);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "1");
    model.result("pg5").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg5").feature("surf1").feature("mtrl1").set("family", "steel");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().named("sel1");
    model.result("pg5").run();
    model.result("pg5").create("slc1", "Slice");
    model.result("pg5").feature("slc1").set("expr", "spf.mu");
    model.result("pg5").feature("slc1").set("quickplane", "xy");
    model.result("pg5").feature("slc1").set("quickznumber", 3);
    model.result("pg5").feature("slc1").set("colortable", "Tectocoris");
    model.result("pg5").feature("slc1").set("colorscalemode", "logarithmic");
    model.result("pg5").run();
    model.result("pg5").set("edges", false);
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("slc1").set("expr", "spf.sr");
    model.result("pg6").run();
    model.result("pg1").run();
    model.result().duplicate("pg7", "pg1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("slc1").set("expr", "T");
    model.result("pg7").run();
    model.result("pg7").feature("arwv1").set("color", "gray");
    model.result("pg7").run();

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("smooth", 0.5);
    model.component("comp1").func("step1").set("location", ".5");
    model.component("comp1").func("step1").set("smooth", 1);

    model.component("comp1").common("rot1").set("revolutionsPerTime", "-1/3*step1(t)");
    model.component("comp1").common("rot2").set("revolutionsPerTime", "1/3*step1(t)");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/spf", true);
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/nitf1", true);
    model.study("std2").feature("time").set("tlist", "range(0,0.1,2)");

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("spf").prop("ConsistentStabilization")
         .set("limitTimeStepInStabilization", true);

    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").feature("comp1_p").set("resscalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_p").set("resscaleval", "1e5");
    model.sol("sol2").feature("v1").feature("comp1_T").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_T").set("scaleval", 350);
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u901f\u5ea6 (spf) 2");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevel", 21, 0);
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").feature().create("slc1", "Slice");
    model.result("pg8").feature("slc1").label("\u5207\u9762");
    model.result("pg8").feature("slc1").set("showsolutionparams", "on");
    model.result("pg8").feature("slc1").set("smooth", "internal");
    model.result("pg8").feature("slc1").set("showsolutionparams", "on");
    model.result("pg8").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").label("\u5916\u58c1 1");
    model.result().dataset("surf2").set("data", "dset2");
    model.result().dataset("surf2").selection().geom("geom1", 2);
    model.result().dataset("surf2").selection()
         .set(1, 2, 3, 4, 8, 10, 11, 12, 13, 17, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 116, 117, 118);
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u538b\u529b (spf) 1");
    model.result("pg9").set("data", "surf2");
    model.result("pg9").setIndex("looplevel", 21, 0);
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").feature().create("surf1", "Surface");
    model.result("pg9").feature("surf1").label("\u8868\u9762");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("expr", "p");
    model.result("pg9").feature("surf1").set("colortable", "Dipole");
    model.result("pg9").feature("surf1").set("smooth", "internal");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("data", "parent");
    model.result("pg9").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").setIndex("looplevel", 21, 0);
    model.result("pg10").feature().create("vol1", "Volume");
    model.result("pg10").feature("vol1").set("showsolutionparams", "on");
    model.result("pg10").feature("vol1").set("solutionparams", "parent");
    model.result("pg10").feature("vol1").set("expr", "T");
    model.result("pg10").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg10").feature("vol1").set("smooth", "internal");
    model.result("pg10").feature("vol1").set("showsolutionparams", "on");
    model.result("pg10").feature("vol1").set("data", "parent");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1) 1");
    model.result("pg11").set("data", "dset2");
    model.result("pg11").setIndex("looplevel", 21, 0);
    model.result("pg11").set("showlegendsunit", true);
    model.result("pg11").feature().create("surf1", "Surface");
    model.result("pg11").feature("surf1").label("\u58c1\u6e29");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("solutionparams", "parent");
    model.result("pg11").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg11").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg11").feature("surf1").set("smooth", "internal");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("data", "parent");
    model.result("pg11").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg11").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg11").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118);
    model.result("pg11").feature().create("arwv1", "ArrowVolume");
    model.result("pg11").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg11").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg11").feature("arwv1").set("solutionparams", "parent");
    model.result("pg11").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg11").feature("arwv1").set("xnumber", 30);
    model.result("pg11").feature("arwv1").set("ynumber", 30);
    model.result("pg11").feature("arwv1").set("znumber", 30);
    model.result("pg11").feature("arwv1").set("arrowtype", "cone");
    model.result("pg11").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg11").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg11").feature("arwv1").set("data", "parent");
    model.result("pg11").feature("arwv1").feature().create("col1", "Color");
    model.result("pg11").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg11").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg11").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("slc1").set("quickplane", "xy");
    model.result("pg8").feature("slc1").set("quickznumber", 3);
    model.result("pg8").run();
    model.result("pg7").run();
    model.result().duplicate("pg12", "pg7");
    model.result("pg12").run();
    model.result("pg6").run();
    model.result().duplicate("pg13", "pg6");
    model.result("pg13").run();
    model.result("pg12").run();
    model.result("pg12").set("data", "dset2");
    model.result("pg12").run();
    model.result("pg13").run();
    model.result("pg13").set("data", "dset2");
    model.result("pg13").run();

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set("groupcontang", true);
    model.component("comp1").selection("sel2").set("angletol", 50);
    model.component("comp1").selection("sel2")
         .add(19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 61, 62, 63, 65, 66, 67, 68);
    model.component("comp1").selection().duplicate("sel3", "sel2");
    model.component("comp1").selection("sel3")
         .remove(19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 61, 62, 63, 65, 66, 67, 68);
    model.component("comp1").selection("sel3")
         .add(69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 116, 117, 118);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("sel2");
    model.component("comp1").cpl().duplicate("intop2", "intop1");
    model.component("comp1").cpl("intop2").selection().named("sel3");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("T1", "intop1(x*spf.T_stressy-y*spf.T_stressx)");
    model.component("comp1").variable("var1").set("T2", "intop2(x*spf.T_stressy-y*spf.T_stressx)");

    model.sol("sol2").updateSolution();

    model.result("pg8").run();
    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").run();
    model.result("pg14").set("data", "dset2");
    model.result("pg14").create("glob1", "Global");
    model.result("pg14").feature("glob1").set("markerpos", "datapoints");
    model.result("pg14").feature("glob1").set("linewidth", "preference");
    model.result("pg14").feature("glob1").setIndex("expr", "T1", 0);
    model.result("pg14").feature("glob1").setIndex("expr", "T2", 1);
    model.result("pg14").feature("glob1").set("legend", false);
    model.result("pg14").run();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("data", "dset2");
    model.result().dataset("cpt1").set("pointx", 0);
    model.result().dataset("cpt1").set("pointy", 0);
    model.result().dataset("cpt1").set("pointz", "0.3 0.6");
    model.result().create("pg15", "PlotGroup1D");
    model.result("pg15").run();
    model.result("pg15").set("data", "cpt1");
    model.result("pg15").create("ptgr1", "PointGraph");
    model.result("pg15").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg15").feature("ptgr1").set("linewidth", "preference");
    model.result("pg15").feature("ptgr1").set("expr", "T");
    model.result("pg15").run();
    model.result().create("pg16", "PlotGroup1D");
    model.result("pg16").run();
    model.result("pg16").set("data", "cpt1");
    model.result("pg16").create("ptgr1", "PointGraph");
    model.result("pg16").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg16").feature("ptgr1").set("linewidth", "preference");
    model.result("pg16").feature("ptgr1").set("expr", "spf.sr");
    model.result("pg16").run();

    model.title("\u5185\u90e8\u6405\u62cc\u5668\u4e2d\u7684\u6d41\u52a8");

    model
         .description("\u672c\u6a21\u578b\u7814\u7a76\u5185\u90e8\u6405\u62cc\u5668\u4e2d\u9ad8\u9ecf\u5ea6\u6d41\u4f53\u7684\u975e\u7b49\u6e29\u6d41\u52a8\u3002\u901a\u8fc7\u7ed3\u5408\u4f7f\u7528 Carreau \u975e\u725b\u987f\u9ecf\u5ea6\u6a21\u578b\u548c\u6307\u6570\u70ed\u76f8\u5173\u6027\u6765\u786e\u5b9a\u6d41\u4f53\u7684\u6e29\u5ea6\u548c\u526a\u5207\u76f8\u5173\u9ecf\u5ea6\uff0c\u5e76\u91c7\u7528\u51bb\u7ed3\u8f6c\u5b50\u65b9\u6cd5\u5feb\u901f\u83b7\u5f97\u7a33\u6001\u89e3\u7684\u7ed3\u679c\u3002\u4e3a\u4e86\u8bc4\u4f30\u8f6c\u5b50\u5728\u542f\u52a8\u8fc7\u7a0b\u4e2d\u53d7\u5230\u7684\u626d\u77e9\uff0c\u672c\u4f8b\u91c7\u7528\u77ac\u6001\u4eff\u771f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("internal_mixer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

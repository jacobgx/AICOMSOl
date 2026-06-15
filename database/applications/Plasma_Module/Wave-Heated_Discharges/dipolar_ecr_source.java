/*
 * dipolar_ecr_source.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:08 by COMSOL 6.3.0.290. */
public class dipolar_ecr_source {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Wave-Heated_Discharges");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("mf").create("alf1", "AmperesLawFluid", 2);
    model.component("comp1").physics("mf").feature("alf1").selection().all();

    model.param().set("r0", "0.12");
    model.param().descr("r0", "\u7b49\u79bb\u5b50\u4f53\u6e90\u534a\u5f84");
    model.param().set("z0", "0.24");
    model.param().descr("z0", "\u7b49\u79bb\u5b50\u4f53\u6e90\u9ad8\u5ea6");
    model.param().set("Pin", "20[W]");
    model.param().descr("Pin", "\u8f93\u5165\u529f\u7387");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"r0", "z0"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-z0/2"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.01, 0.03});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0, 0.04});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new double[]{0.004, 0.048});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{0.006, 0.072});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new double[]{0.004, 0.05});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new double[]{0, 0.07});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"r0-0.01", "0.02"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new double[]{0.01, 0.12});
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").set("size", new double[]{0.02, 0.02});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new String[]{"r0", "0.12"});
    model.component("comp1").geom("geom1").run("r6");
    model.component("comp1").geom("geom1").create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("r7").set("size", new String[]{"0.02", "z0"});
    model.component("comp1").geom("geom1").feature("r7").set("pos", new String[]{"r0", "-z0/2"});
    model.component("comp1").geom("geom1").run("r7");
    model.component("comp1").geom("geom1").create("r8", "Rectangle");
    model.component("comp1").geom("geom1").feature("r8").set("size", new double[]{0.02, 0.02});
    model.component("comp1").geom("geom1").feature("r8").set("pos", new String[]{"r0", "-0.02-z0/2"});
    model.component("comp1").geom("geom1").run("r8");
    model.component("comp1").geom("geom1").create("r9", "Rectangle");
    model.component("comp1").geom("geom1").feature("r9").set("size", new String[]{"r0", "0.02"});
    model.component("comp1").geom("geom1").feature("r9").set("pos", new String[]{"0", "-0.02-z0/2"});
    model.component("comp1").geom("geom1").run("r9");
    model.component("comp1").geom("geom1").create("r10", "Rectangle");
    model.component("comp1").geom("geom1").feature("r10").set("size", new double[]{0.01, 0.02});
    model.component("comp1").geom("geom1").feature("r10").set("pos", new double[]{0, 0.12});
    model.component("comp1").geom("geom1").run("r10");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new double[]{0.01, 0});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new double[]{0.01, 0});
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new double[]{0.01, 0.072});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new double[]{0.01, 0.07});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").axis().set("xmin", -0.05);
    model.component("comp1").view("view2").axis().set("xmax", 0.16);
    model.component("comp1").view("view2").axis().set("ymin", -0.02);
    model.component("comp1").view("view2").axis().set("ymax", 0.12);
    model.component("comp1").view("view2").set("locked", true);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(4, 6, 18, 19, 20, 22, 26);
    model.component("comp1").selection("sel1").label("\u58c1");

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").set("ScalingType", "Cylindrical");
    model.component("comp1").coordSystem("ie1").selection().set(1, 5, 8, 9, 10, 11);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().set(1, 2, 4, 5, 7, 8, 9, 10, 11);
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(3);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"6e7"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().set(6);
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"2"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(2);
    model.component("comp1").cpl("intop1").set("axisym", false);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().set(2);

    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").selection().set(3);
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("N", 5000);
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", 14);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(19);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", "0.0005");
    model.component("comp1").mesh("mesh1").create("edg2", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg2").selection().set(6, 18, 20, 22);
    model.component("comp1").mesh("mesh1").feature("edg2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg2").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("edg2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg2").feature("size1").set("hmax", 0.0015);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("ftri2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmax", 0.001);
    model.component("comp1").mesh("mesh1").create("ftri3", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("errestandadap", "adaption");
    model.study("std1").feature("stat").set("meshadaptmethod", "modify");
    model.study("std1").feature("stat").set("errestim", "goalerrest");
    model.study("std1").feature("stat").set("goalfunctype", "gfman");
    model.study("std1").feature("stat").set("gfunc", "comp1.intop1(1/(abs(comp1.mf.normB-0.0875)+1e-4))");
    model.study("std1").feature("stat").set("meshadaptmethod", "rebuild");
    model.study("std1").feature("stat").set("userngen", true);
    model.study("std1").label("\u9759\u78c1\u573a");
    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("adDef").set("maxscale", 1);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(2, 4, 6, 7);
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u7a33\u6001\u78c1\u901a\u5bc6\u5ea6");
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("con1").set("expr", "log10(mf.normB+eps)");
    model.result("pg1").feature("con1").set("number", 8);
    model.result("pg1").feature("con1").set("contourtype", "filled");
    model.result("pg1").feature("con1").set("colortable", "GrayScale");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("colortabletrans", "reverse");
    model.result("pg1").run();
    model.result("pg1").create("con2", "Contour");
    model.result("pg1").feature("con2").set("expr", "Aphi");
    model.result("pg1").feature("con2").set("coloring", "uniform");
    model.result("pg1").feature("con2").set("color", "black");
    model.result("pg1").feature("con2").set("colorlegend", false);
    model.result("pg1").feature().duplicate("con3", "con2");
    model.result("pg1").run();
    model.result("pg1").feature("con3").set("expr", "mf.normB");
    model.result("pg1").feature("con3").set("descr", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.result("pg1").feature("con3").set("levelmethod", "levels");
    model.result("pg1").feature("con3").set("levels", "range(0.086,1.578947368421054e-4,0.089)");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u81ea\u9002\u5e94\u7f51\u683c");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("mesh1", "Mesh");
    model.result("pg2").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg2").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg2").feature("mesh1").set("elemcolor", "none");
    model.result("pg2").feature("mesh1").set("wireframecolor", "custom");
    model.result("pg2").feature("mesh1")
         .set("customwireframecolor", new double[]{0.7529411911964417, 0.7529411911964417, 0.7529411911964417});
    model.result("pg2").run();

    model.component("comp1").physics().create("plas", "ColdPlasma", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/plas", false);

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/emw", false);

    model.component("comp1").physics("emw").prop("ShapeProperty").set("order_electricfield", "1");

    model.component("comp1").multiphysics().create("pcc1", "PlasmaConductivityMultiphysicsCoupling", 2);

    model.study("std1").feature("stat").setSolveFor("/multiphysics/pcc1", false);

    model.component("comp1").multiphysics("pcc1").set("elm_physics", "emw");
    model.component("comp1").multiphysics("pcc1").set("plas_physics", "plas");
    model.component("comp1").multiphysics().create("ehs1", "ElectronHeatSourceMultiphysicsCoupling", 2);

    model.study("std1").feature("stat").setSolveFor("/multiphysics/ehs1", false);

    model.component("comp1").multiphysics("ehs1").set("elm_physics", "emw");
    model.component("comp1").multiphysics("ehs1").set("plas_physics", "plas");

    model.component("comp1").physics("plas").selection().set(1, 2, 5, 6, 8, 9, 10, 11);
    model.component("comp1").physics("emw").selection().set(2, 6);
    model.component("comp1").physics("plas").prop("TransportSettings").set("fullDiffusivity", true);
    model.component("comp1").physics("plas").prop("TransportSettings").set("IonTensorProps", true);
    model.component("comp1").physics("plas").prop("ElectronProperties").set("TensorElectronProps", true);

    model.component("comp1").multiphysics("pcc1").set("TensorSigma", true);
    model.component("comp1").multiphysics("pcc1").set("dopplerb", 20);

    model.component("comp1").physics("plas").selection().set(2);
    model.component("comp1").physics("plas").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("plas").feature("xsec1").set("Filepath", "Ar_xsecs.txt");
    model.component("comp1").physics("plas").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("plas").create("rxn1", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn1").set("formula", "Ars+Ar=>Ar+Ar");
    model.component("comp1").physics("plas").feature("rxn1").set("kf", 1807);
    model.component("comp1").physics("plas").create("rxn2", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn2").set("formula", "Ars+Ars=>e+Ar+Ar+");
    model.component("comp1").physics("plas").feature("rxn2").set("kf", "3.734E8");
    model.component("comp1").physics("plas").feature("Ar").set("FromMassConstraint", true);
    model.component("comp1").physics("plas").feature("Ar").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("Ars").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("Ars").set("x0", "1E-4");
    model.component("comp1").physics("plas").feature("Ar_1p").set("InitIon", true);
    model.component("comp1").physics("plas").feature("Ar_1p").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("Ar_1p")
         .set("MobilityDiffusivitySpecification", "SpecifyMobilityComputeDiffusivity");
    model.component("comp1").physics("plas").feature("Ar_1p").set("MobilitySpecification", "SpecifyLookupMobility");
    model.component("comp1").physics("plas").feature("Ar_1p")
         .set("efield_mueXdata", new String[]{"0", "0.000E+00", "1.931E+00", "2.414E+00", "2.897E+00", "3.621E+00", "4.828E+00", "6.035E+00", "7.242E+00", "9.656E+00", 
         "1.207E+01", "1.448E+01", "1.931E+01", "2.414E+01", "2.897E+01", "3.621E+01", "4.828E+01", "6.035E+01", "7.242E+01", "9.656E+01", 
         "1.207E+02", "1.448E+02", "1.931E+02", "2.414E+02", "2.897E+02", "3.621E+02", "4.828E+02", "5.251E+02", "6.001E+02", "6.751E+02", 
         "7.501E+02", "1e3", "5e3", "1e5", "1e6"});
    model.component("comp1").physics("plas").feature("Ar_1p")
         .set("efield_mueYdata", new double[]{0, 16.984765, 16.984765, 16.984765, 16.984765, 16.873843, 16.762788, 16.540811, 16.318701, 15.985669, 15.652637, 15.319605, 14.653541, 14.098532, 13.54339, 12.8773792, 11.7672548, 10.9901757, 10.5461286, 9.4360042, 8.6589251, 7.9928478, 6.9937518, 6.2166594, 5.6616105, 5.1065483, 4.440471, 4.2579019, 3.9828978, 3.7551087, 3.5624183, 3, 1.5, 1, 0.8});
    model.component("comp1").physics("plas").create("sr1", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr1").set("formula", "Ar+=>Ar");
    model.component("comp1").physics("plas").feature("sr1").selection().named("sel1");
    model.component("comp1").physics("plas").create("sr2", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr2").set("formula", "Ars=>Ar");
    model.component("comp1").physics("plas").feature("sr2").selection().named("sel1");
    model.component("comp1").physics("plas").create("wall1", "WallDriftDiffusion", 1);
    model.component("comp1").physics("plas").feature("wall1").selection().named("sel1");
    model.component("comp1").physics("plas").create("gnd1", "Ground", 1);
    model.component("comp1").physics("plas").feature("gnd1").selection().named("sel1");
    model.component("comp1").physics("plas").feature("init1").set("neinit", "1E17[1/m^3]");
    model.component("comp1").physics("plas").feature("init1").set("ebarinit", "2[V]");
    model.component("comp1").physics("plas").feature("pes1").set("B_src", "root.comp1.mf.Br");
    model.component("comp1").physics("plas").feature("pes1").set("T", 300);
    model.component("comp1").physics("plas").feature("pes1").set("pA", 1);
    model.component("comp1").physics("plas").feature("pes1").set("mudc", "1E25[1/(m*V*s)]/plas.Nn");
    model.component("comp1").physics("emw").create("port1", "Port", 1);
    model.component("comp1").physics("emw").feature("port1").selection().set(14);
    model.component("comp1").physics("emw").feature("port1").set("PortType", "Coaxial");
    model.component("comp1").physics("emw").feature("port1").set("Pin", "Pin");

    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").create("rf1", "Reference");
    model.component("comp1").mesh("mesh2").feature("rf1").set("sequence", "adaptmesh1");
    model.component("comp1").mesh("mesh2").create("ref1", "Refine");
    model.component("comp1").mesh("mesh2").feature("ref1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh2").feature("ref1").selection().set(6);
    model.component("comp1").mesh("mesh2").feature("ref1").set("numrefine", 2);
    model.component("comp1").mesh("mesh2").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh2").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh2").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh2").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh2").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh2").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh2").feature("bl1").selection().set(2);
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp").selection().named("sel1");
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp").set("blnlayers", 4);
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp").set("blstretch", 1.4);
    model.component("comp1").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("ftrans", "FrequencyTransient");
    model.study("std2").feature("ftrans").set("plotgroup", "Default");
    model.study("std2").feature("ftrans").set("initialtime", "0");
    model.study("std2").feature("ftrans").set("freq", "1000");
    model.study("std2").feature("ftrans").set("solnum", "auto");
    model.study("std2").feature("ftrans").set("notsolnum", "auto");
    model.study("std2").feature("ftrans").set("outputmap", new String[]{});
    model.study("std2").feature("ftrans").setSolveFor("/physics/mf", false);
    model.study("std2").feature("ftrans").setSolveFor("/physics/plas", true);
    model.study("std2").feature("ftrans").setSolveFor("/physics/emw", true);
    model.study("std2").feature("ftrans").setSolveFor("/multiphysics/pcc1", true);
    model.study("std2").feature("ftrans").setSolveFor("/multiphysics/ehs1", true);
    model.study("std2").feature("ftrans").set("tlist", "0 10^{range(-8,6/10,0)}");
    model.study("std2").feature("ftrans").set("freq", 2.45E9);
    model.study("std2").feature("ftrans").set("usesol", true);
    model.study("std2").feature("ftrans").set("notsolmethod", "sol");
    model.study("std2").feature("ftrans").set("notstudy", "std1");
    model.study("std2").feature("ftrans").set("notsol", "sol2");
    model.study("std2").feature("ftrans").set("notsoluse", "sol4");
    model.study("std2").showAutoSequences("all");

    model.sol("sol5").feature("v1").set("control", "user");

    model.study("std2").label("Pin=20 W");
    model.study("std2").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 15, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"plas.ne"});
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 15, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"plas.Te"});
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 15, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"V"});
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg5").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").label("\u7535\u5b50\u5bc6\u5ea6 (plas)");
    model.result("pg4").label("\u7535\u5b50\u6e29\u5ea6 (plas)");
    model.result("pg5").label("\u7535\u52bf (plas)");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u7535\u573a (emw)");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 15, 0);
    model.result("pg6").set("dataisaxisym", "off");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("expr", "emw.normE");
    model.result("pg6").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset3");
    model.result("pg3").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").placeAfter("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").add("plotgroup", "pg5");
    model.nodeGroup("grp1").add("plotgroup", "pg6");
    model.nodeGroup("grp1").label("Pin=20 W");

    model.study().create("std3");
    model.study("std3").create("fstat", "FrequencyStationary");
    model.study("std3").feature("fstat").set("plotgroup", "Default");
    model.study("std3").feature("fstat").set("freq", "1000000");
    model.study("std3").feature("fstat").set("solnum", "auto");
    model.study("std3").feature("fstat").set("notsolnum", "auto");
    model.study("std3").feature("fstat").set("outputmap", new String[]{});
    model.study("std3").feature("fstat").set("ngenAUX", "1");
    model.study("std3").feature("fstat").set("goalngenAUX", "1");
    model.study("std3").feature("fstat").set("ngenAUX", "1");
    model.study("std3").feature("fstat").set("goalngenAUX", "1");
    model.study("std3").feature("fstat").setSolveFor("/physics/mf", true);
    model.study("std3").feature("fstat").setSolveFor("/physics/plas", true);
    model.study("std3").feature("fstat").setSolveFor("/physics/emw", true);
    model.study("std3").feature("fstat").setSolveFor("/multiphysics/pcc1", true);
    model.study("std3").feature("fstat").setSolveFor("/multiphysics/ehs1", true);
    model.study("std3").feature("fstat").set("freq", "2.45e9");
    model.study("std3").feature("fstat").setSolveFor("/physics/mf", false);
    model.study("std3").feature("fstat").set("useinitsol", true);
    model.study("std3").feature("fstat").set("initmethod", "sol");
    model.study("std3").feature("fstat").set("initstudy", "std2");
    model.study("std3").feature("fstat").set("usesol", true);
    model.study("std3").feature("fstat").set("notsolmethod", "sol");
    model.study("std3").feature("fstat").set("notstudy", "std1");
    model.study("std3").feature("fstat").set("notsol", "sol2");
    model.study("std3").feature("fstat").set("useparam", true);
    model.study("std3").feature("fstat").setIndex("pname", "r0", 0);
    model.study("std3").feature("fstat").setIndex("plistarr", "", 0);
    model.study("std3").feature("fstat").setIndex("punit", "", 0);
    model.study("std3").feature("fstat").setIndex("pname", "r0", 0);
    model.study("std3").feature("fstat").setIndex("plistarr", "", 0);
    model.study("std3").feature("fstat").setIndex("punit", "", 0);
    model.study("std3").feature("fstat").setIndex("pname", "Pin", 0);
    model.study("std3").feature("fstat").setIndex("plistarr", "range(20,10,70) 73.5 75 80", 0);
    model.study("std3").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset4");
    model.result("pg7").setIndex("looplevel", 9, 0);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"plas.ne"});
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset4");
    model.result("pg8").setIndex("looplevel", 9, 0);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"plas.Te"});
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset4");
    model.result("pg9").setIndex("looplevel", 9, 0);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"V"});
    model.result("pg7").feature("surf1").set("colortable", "Prism");
    model.result("pg8").feature("surf1").set("colortable", "Prism");
    model.result("pg9").feature("surf1").set("colortable", "Dipole");
    model.result("pg7").label("\u7535\u5b50\u5bc6\u5ea6 (plas) 1");
    model.result("pg8").label("\u7535\u5b50\u6e29\u5ea6 (plas) 1");
    model.result("pg9").label("\u7535\u52bf (plas) 1");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").label("\u7535\u573a (emw) 1");
    model.result("pg10").set("data", "dset4");
    model.result("pg10").setIndex("looplevel", 9, 0);
    model.result("pg10").set("dataisaxisym", "off");
    model.result("pg10").set("frametype", "spatial");
    model.result("pg10").set("showlegendsmaxmin", true);
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").label("\u8868\u9762");
    model.result("pg10").feature("surf1").set("expr", "emw.normE");
    model.result("pg10").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg10").feature("surf1").set("smooth", "internal");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").label("\u4e8c\u7ef4\u65cb\u8f6c 1");
    model.result().dataset("rev2").set("data", "none");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("data", "dset4");
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").set("data", "dset4");
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("unit", new String[]{""});
    model.result("pg11").feature("glob1").set("expr", new String[]{"emw.S11dB"});
    model.result("pg11").feature("glob1").set("descr", new String[]{"S11"});
    model.result("pg11").label("S \u53c2\u6570 (emw)");
    model.result("pg11").feature("glob1").set("titletype", "none");
    model.result("pg11").feature("glob1").set("xdata", "expr");
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg11").feature("glob1").set("xdataexpr", "Pin");
    model.result("pg11").feature("glob1").set("xdataunit", "W");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("xdatasolnumtype", "all");

    return model;
  }

  public static Model run2(Model model) {
    model.result().create("pg12", "SmithGroup");
    model.result("pg12").set("data", "dset4");
    model.result("pg12").create("rgr1", "ReflectionGraph");
    model.result("pg12").feature("rgr1").set("unit", new String[]{""});
    model.result("pg12").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg12").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg12").label("\u53f2\u5bc6\u65af\u56fe (emw)");
    model.result("pg12").feature("rgr1").set("titletype", "manual");
    model.result("pg12").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg12").feature("rgr1").set("linemarker", "point");
    model.result("pg12").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg12").feature("rgr1").create("col1", "Color");
    model.result("pg12").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg12").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result("pg7").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup("grp2").placeAfter("plotgroup", "pg2");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").add("plotgroup", "pg8");
    model.nodeGroup("grp2").add("plotgroup", "pg9");
    model.nodeGroup("grp2").add("plotgroup", "pg10");
    model.nodeGroup("grp2").add("plotgroup", "pg11");
    model.nodeGroup("grp2").add("plotgroup", "pg12");
    model.nodeGroup("grp2").label("Pin 20~80 W");

    model.study("std3").label("Pin 20~80 W");

    model.result("pg7").run();
    model.result("pg7").setIndex("looplevel", 7, 0);
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 7, 0);
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", 7, 0);
    model.result("pg9").run();
    model.result("pg10").run();
    model.result("pg10").setIndex("looplevel", 7, 0);
    model.result("pg10").run();
    model.result().create("pg13", "PlotGroup1D");

    model.nodeGroup("grp2").add("plotgroup", "pg13");

    model.result("pg13").run();
    model.result("pg13").label("\u5438\u6536\u529f\u7387 vs. \u8f93\u5165\u529f\u7387");
    model.result("pg13").set("data", "dset4");
    model.result("pg13").set("showlegends", false);
    model.result("pg13").set("titletype", "none");
    model.result("pg13").create("glob1", "Global");
    model.result("pg13").feature("glob1").set("markerpos", "datapoints");
    model.result("pg13").feature("glob1").set("linewidth", "preference");
    model.result("pg13").feature("glob1").setIndex("expr", "intop2(emw.Qrh)", 0);
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").set("xlabelactive", true);
    model.result("pg13").set("xlabel", "\u8f93\u5165\u529f\u7387 (W)");
    model.result("pg13").set("ylabelactive", true);
    model.result("pg13").set("ylabel", "\u5438\u6536\u529f\u7387 (W)");
    model.result("pg13").run();
    model.result().create("pg14", "PlotGroup2D");

    model.nodeGroup("grp2").add("plotgroup", "pg14");

    model.result("pg14").run();
    model.result("pg14").set("data", "dset4");
    model.result("pg14").setIndex("looplevel", 7, 0);
    model.result("pg14").label("\u529f\u7387\u6c89\u79ef");
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", "emw.Qrh");
    model.result("pg14").feature("surf1").set("colortable", "Prism");
    model.result("pg14").run();
    model.result("pg14").run();
    model.result().duplicate("pg15", "pg14");

    model.nodeGroup("grp2").add("plotgroup", "pg15");

    model.result("pg15").run();
    model.result("pg15").label("\u7535\u5b50\u6e90");
    model.result("pg15").run();
    model.result("pg15").feature("surf1").set("expr", "plas.Re");
    model.result("pg15").run();
    model.result("pg15").run();
    model.result().duplicate("pg16", "pg15");

    model.nodeGroup("grp2").add("plotgroup", "pg16");

    model.result("pg16").run();
    model.result("pg16").label("\u7535\u5b50\u8fc1\u79fb\u7387\uff0crr \u5206\u91cf");
    model.result("pg16").run();
    model.result("pg16").feature("surf1").set("expr", "plas.muerr");
    model.result("pg16").run();
    model.result("pg16").run();
    model.result().duplicate("pg17", "pg16");

    model.nodeGroup("grp2").add("plotgroup", "pg17");

    model.result("pg17").run();
    model.result("pg17").label("\u7535\u5b50\u8fc1\u79fb\u7387\uff0czz \u5206\u91cf");
    model.result("pg17").run();
    model.result("pg17").feature("surf1").set("expr", "plas.muezz");
    model.result("pg17").run();
    model.result("pg17").run();
    model.result().duplicate("pg18", "pg17");

    model.nodeGroup("grp2").add("plotgroup", "pg18");

    model.result("pg18").run();
    model.result("pg18").label("\u4f20\u5bfc\u7535\u6d41\u5bc6\u5ea6\uff0cr \u5206\u91cf");
    model.result("pg18").run();
    model.result("pg18").feature("surf1").set("expr", "emw.Jr-emw.Jdr");
    model.result("pg18").run();
    model.result("pg18").run();
    model.result().duplicate("pg19", "pg18");

    model.nodeGroup("grp2").add("plotgroup", "pg19");

    model.result("pg19").run();
    model.result("pg19").label("\u4f20\u5bfc\u7535\u6d41\u5bc6\u5ea6\uff0cz \u5206\u91cf");
    model.result("pg19").run();
    model.result("pg19").feature("surf1").set("expr", "emw.Jz-emw.Jdz");
    model.result("pg19").run();
    model.result("pg19").run();
    model.result().duplicate("pg20", "pg19");

    model.nodeGroup("grp2").add("plotgroup", "pg20");

    model.result("pg20").run();
    model.result("pg20").label("\u4f20\u5bfc\u7535\u6d41\u5bc6\u5ea6\uff0cphi \u5206\u91cf");
    model.result("pg20").run();
    model.result("pg20").feature("surf1").set("expr", "emw.Jphi-emw.Jdphi");
    model.result("pg20").run();
    model.result("pg20").run();
    model.result().duplicate("pg21", "pg20");

    model.nodeGroup("grp2").add("plotgroup", "pg21");

    model.result("pg21").run();
    model.result("pg21").label("\u5e73\u5747\u7b49\u79bb\u5b50\u4f53\u7535\u5bfc\u7387");
    model.result("pg21").run();
    model.result("pg21").feature("surf1").set("expr", "(emw.sigmarr+emw.sigmaphiphi+emw.sigmazz)/3");
    model.result("pg21").run();
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg13").run();
    model.result("pg14").run();
    model.result("pg15").run();
    model.result("pg16").run();
    model.result("pg17").run();
    model.result("pg18").run();
    model.result("pg19").run();
    model.result("pg20").run();
    model.result("pg21").run();

    model.title("\u5076\u6781\u5fae\u6ce2\u7b49\u79bb\u5b50\u4f53\u6e90");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u4e00\u79cd\u7531\u7535\u5b50\u56de\u65cb\u5171\u632f (ECR) \u7ef4\u6301\u7684\u5076\u6781\u5fae\u6ce2\u7b49\u79bb\u5b50\u4f53\u6e90\u3002\u7535\u78c1\u6ce2\u5411\u7b49\u79bb\u5b50\u4f53\u7684\u529f\u7387\u6c89\u79ef\u53d1\u751f\u5728\u4e00\u4e2a\u975e\u5e38\u8584\u7684\u5171\u632f\u533a\u57df\uff0c\u6b64\u5904\u7684\u7a33\u6001\u78c1\u901a\u5bc6\u5ea6\u7b49\u4e8e\u5171\u632f\u78c1\u901a\u5bc6\u5ea6 (0.0875\u00a0T)\u3002\n\n\u672c\u4f8b\u9700\u8981\u201c\u7b49\u79bb\u5b50\u4f53\u6a21\u5757\u201d\u3001\u201cAC/DC \u6a21\u5757\u201d\u548c\u201cRF \u6a21\u5757\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("dipolar_ecr_source.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}

/*
 * thermal_plasma.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:06 by COMSOL 6.3.0.290. */
public class thermal_plasma {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Inductively_Coupled_Plasmas");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("plas", "ColdPlasma", "geom1");
    model.component("comp1").physics("plas").prop("TransportSettings").set("calcThermo", "1");
    model.component("comp1").physics("plas").prop("TransportSettings").set("Convection", "1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").feature("fluid1").set("fluidType", new String[]{"gasLiquid"});
    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics("mf").prop("ShapeProperty").set("order_magneticvectorpotential", "1");
    model.component("comp1").physics("mf").create("alf1", "AmperesLawFluid");
    model.component("comp1").physics("mf").feature("alf1").selection().all();

    model.component("comp1").multiphysics().create("nipf1", "NonIsothermalPlasmaFlowMultiphysicsCoupling", 2);
    model.component("comp1").multiphysics("nipf1").set("plas_physics", "plas");
    model.component("comp1").multiphysics("nipf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nipf1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics().create("pcc1", "PlasmaConductivityMultiphysicsCoupling", 2);
    model.component("comp1").multiphysics("pcc1").set("elm_physics", "mf");
    model.component("comp1").multiphysics("pcc1").set("plas_physics", "plas");
    model.component("comp1").multiphysics().create("ehs1", "ElectronHeatSourceMultiphysicsCoupling", 2);
    model.component("comp1").multiphysics("ehs1").set("elm_physics", "mf");
    model.component("comp1").multiphysics("ehs1").set("plas_physics", "plas");

    model.study().create("std1");
    model.study("std1").create("ftrans", "FrequencyTransient");
    model.study("std1").feature("ftrans").set("initialtime", "0");
    model.study("std1").feature("ftrans").set("freq", "1000");
    model.study("std1").feature("ftrans").set("solnum", "auto");
    model.study("std1").feature("ftrans").set("notsolnum", "auto");
    model.study("std1").feature("ftrans").set("outputmap", new String[]{});
    model.study("std1").feature("ftrans").setSolveFor("/physics/plas", true);
    model.study("std1").feature("ftrans").setSolveFor("/physics/spf", true);
    model.study("std1").feature("ftrans").setSolveFor("/physics/ht", true);
    model.study("std1").feature("ftrans").setSolveFor("/physics/mf", true);
    model.study("std1").feature("ftrans").setSolveFor("/multiphysics/nipf1", true);
    model.study("std1").feature("ftrans").setSolveFor("/multiphysics/pcc1", true);
    model.study("std1").feature("ftrans").setSolveFor("/multiphysics/ehs1", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "thermal_plasma.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.param().set("p0", "1[torr]");
    model.param().descr("p0", "\u521d\u59cb\u548c\u51fa\u53e3\u538b\u529b");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection("sel1").label("\u7b49\u79bb\u5b50\u4f53");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(9, 10, 34, 35);
    model.component("comp1").selection("sel2").label("\u58c1");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(4);
    model.component("comp1").selection("sel3").label("\u51fa\u53e3");
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(1);
    model.component("comp1").selection("sel4").set(12, 13, 15, 16, 17, 19, 21, 22, 24, 25, 26, 28, 29, 30, 31, 32);
    model.component("comp1").selection("sel4").label("\u7ebf\u5708\u58c1");

    model.component("comp1").physics("plas").selection().named("sel1");
    model.component("comp1").physics("plas").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("plas").feature("xsec1").set("Filepath", "Ar_xsecs.txt");
    model.component("comp1").physics("plas").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("plas").prop("ElectronProperties").set("ReducedProps", true);
    model.component("comp1").physics("plas").create("rxn1", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn1").set("formula", "Ars+Ars=>e+Ar+Ar+");
    model.component("comp1").physics("plas").feature("rxn1").set("kf", "3.734E8");
    model.component("comp1").physics("plas").create("rxn2", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn2").set("formula", "Ars+Ar=>Ar+Ar");
    model.component("comp1").physics("plas").feature("rxn2").set("kf", 1807);
    model.component("comp1").physics("plas").create("gnd1", "Ground", 1);
    model.component("comp1").physics("plas").feature("gnd1").selection().named("sel2");
    model.component("comp1").physics("plas").create("sr1", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr1").selection().named("sel2");
    model.component("comp1").physics("plas").feature("sr1").set("formula", "Ar+=>Ar");
    model.component("comp1").physics("plas").create("sr2", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr2").selection().named("sel2");
    model.component("comp1").physics("plas").feature("sr2").set("formula", "Ars=>Ar");
    model.component("comp1").physics("plas").create("wall1", "WallDriftDiffusion", 1);
    model.component("comp1").physics("plas").feature("wall1").selection().named("sel2");
    model.component("comp1").physics("plas").create("out1", "Outflow", 1);
    model.component("comp1").physics("plas").feature("out1").selection().named("sel3");
    model.component("comp1").physics("plas").feature("out1").set("IncludeIons", true);
    model.component("comp1").physics("plas").feature("Ar").set("FromMassConstraint", true);
    model.component("comp1").physics("plas").feature("Ar").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("Ars").set("x0", "1E-4");
    model.component("comp1").physics("plas").feature("Ars").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("Ars").set("hadd", 11.5);
    model.component("comp1").physics("plas").feature("Ar_1p").set("InitIon", true);
    model.component("comp1").physics("plas").feature("Ar_1p").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("Ar_1p").set("hadd", 15.8);
    model.component("comp1").physics("plas").feature("pes1")
         .set("muN", new String[]{"4E24", "0", "0", "0", "4E24", "0", "0", "0", "4E24"});
    model.component("comp1").physics("plas").feature("init1").set("neinit", "1E15");
    model.component("comp1").physics("plas").feature("init1").set("ebarinit", 3);
    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").selection().set(4, 5, 6, 7);
    model.component("comp1").physics("mf").feature("coil1").set("CoilExcitation", "Power");
    model.component("comp1").physics("mf").feature("coil1").set("coilGroup", true);
    model.component("comp1").physics("mf").feature("coil1").set("PCoil", "700[W]");
    model.component("comp1").physics("spf").prop("ConsistentStabilization").set("CrosswindDiffusion", false);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("pref", "p0");
    model.component("comp1").physics("spf").selection().set(1);
    model.component("comp1").physics("spf").prop("EquationForm").setIndex("form", "Stationary", 0);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "MassFlow");
    model.component("comp1").physics("spf").feature("inl1").set("MassFlowType", "StandardFlowRateSCCM");
    model.component("comp1").physics("spf").feature("inl1").set("sccmmfr", "100*tanh(1E5*t[1/s])");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(4);
    model.component("comp1").physics("ht").selection().set(1);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", 300);
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(2, 9, 10, 34, 35);
    model.component("comp1").physics("ht").feature("temp1").set("T0", 300);
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(4);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().set(2);
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"4.5"});
    model.component("comp1").material("mat1").label("\u7535\u4ecb\u8d28");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(3);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat2").label("\u7a7a\u6c14");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().set(4, 5, 6, 7);
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"6E7"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat3").label("\u94dc\u7ebf\u5708");

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", 0.001);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.5);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(4, 5, 6, 7);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().named("sel4");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 35);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("ftrans").set("tlist", "0 10^{range(-8,0.3,-2)}");
    model.study("std1").feature("ftrans").set("freq", "13.56E6");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 22, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"plas.ne"});
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 22, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"plas.Te"});
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 22, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"V"});
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg1").label("\u7535\u5b50\u5bc6\u5ea6 (plas)");
    model.result("pg2").label("\u7535\u5b50\u6e29\u5ea6 (plas)");
    model.result("pg3").label("\u7535\u52bf (plas)");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u901f\u5ea6 (spf)");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "spf.U");
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u538b\u529b (spf)");
    model.result("pg5").set("dataisaxisym", "off");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("con1", "Contour");
    model.result("pg5").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("expr", "p");
    model.result("pg5").feature("con1").set("number", 40);
    model.result("pg5").feature("con1").set("levelrounding", false);
    model.result("pg5").feature("con1").set("colortable", "Rainbow");
    model.result("pg5").feature("con1").set("smooth", "internal");
    model.result("pg5").feature("con1").set("showsolutionparams", "on");
    model.result("pg5").feature("con1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("expr", "spf.U");
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u6e29\u5ea6 (ht)");
    model.result("pg7").set("dataisaxisym", "off");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("solutionparams", "parent");
    model.result("pg7").feature("surf1").set("expr", "T");
    model.result("pg7").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg8").set("dataisaxisym", "off");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").set("showlegendsmaxmin", true);
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("solutionparams", "parent");
    model.result("pg8").feature("surf1").set("expr", "mf.normB");
    model.result("pg8").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg8").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result("pg8").feature().create("str1", "Streamline");
    model.result("pg8").feature("str1").set("showsolutionparams", "on");
    model.result("pg8").feature("str1").set("solutionparams", "parent");
    model.result("pg8").feature("str1").set("expr", new String[]{"mf.Br", "mf.Bz"});
    model.result("pg8").feature("str1").set("titletype", "none");
    model.result("pg8").feature("str1").set("posmethod", "uniform");
    model.result("pg8").feature("str1").set("udist", 0.03);
    model.result("pg8").feature("str1").set("maxlen", 0.4);
    model.result("pg8").feature("str1").set("maxsteps", 5000);
    model.result("pg8").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("str1").set("inheritcolor", false);
    model.result("pg8").feature("str1").set("showsolutionparams", "on");
    model.result("pg8").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("str1").set("showsolutionparams", "on");
    model.result("pg8").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("str1").set("showsolutionparams", "on");
    model.result("pg8").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("str1").set("showsolutionparams", "on");
    model.result("pg8").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("str1").set("data", "parent");
    model.result("pg8").feature("str1").selection().geom("geom1", 1);
    model.result("pg8").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37);
    model.result("pg8").feature("str1").set("inheritplot", "surf1");
    model.result("pg8").feature("str1").feature().create("col1", "Color");
    model.result("pg8").feature("str1").feature("col1").set("expr", "mf.normB");
    model.result("pg8").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg8").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg8").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg8").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg8").feature("str1").feature().create("filt1", "Filter");
    model.result("pg8").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg8").feature().create("con1", "Contour");
    model.result("pg8").feature("con1").set("showsolutionparams", "on");
    model.result("pg8").feature("con1").set("solutionparams", "parent");
    model.result("pg8").feature("con1").set("expr", "mf.Psi");
    model.result("pg8").feature("con1").set("titletype", "none");
    model.result("pg8").feature("con1").set("number", 10);
    model.result("pg8").feature("con1").set("levelrounding", false);
    model.result("pg8").feature("con1").set("coloring", "uniform");
    model.result("pg8").feature("con1").set("colorlegend", false);
    model.result("pg8").feature("con1").set("color", "custom");
    model.result("pg8").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg8").feature("con1").set("resolution", "fine");
    model.result("pg8").feature("con1").set("inheritcolor", false);
    model.result("pg8").feature("con1").set("showsolutionparams", "on");
    model.result("pg8").feature("con1").set("data", "parent");
    model.result("pg8").feature("con1").set("inheritplot", "surf1");
    model.result("pg8").feature("con1").feature().create("filt1", "Filter");
    model.result("pg8").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf)");
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").set("showlegendsmaxmin", true);
    model.result("pg9").feature().create("vol1", "Volume");
    model.result("pg9").feature("vol1").set("showsolutionparams", "on");
    model.result("pg9").feature("vol1").set("solutionparams", "parent");
    model.result("pg9").feature("vol1").set("expr", "mf.normB");
    model.result("pg9").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg9").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg9").feature("vol1").set("showsolutionparams", "on");
    model.result("pg9").feature("vol1").set("data", "parent");
    model.result("pg9").feature().create("con1", "Contour");
    model.result("pg9").feature("con1").set("showsolutionparams", "on");
    model.result("pg9").feature("con1").set("solutionparams", "parent");
    model.result("pg9").feature("con1").set("expr", "mf.Psi");
    model.result("pg9").feature("con1").set("titletype", "none");
    model.result("pg9").feature("con1").set("number", 10);
    model.result("pg9").feature("con1").set("levelrounding", false);
    model.result("pg9").feature("con1").set("coloring", "uniform");
    model.result("pg9").feature("con1").set("colorlegend", false);
    model.result("pg9").feature("con1").set("color", "custom");
    model.result("pg9").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg9").feature("con1").set("resolution", "fine");
    model.result("pg9").feature("con1").set("inheritcolor", false);
    model.result("pg9").feature("con1").set("showsolutionparams", "on");
    model.result("pg9").feature("con1").set("data", "parent");
    model.result("pg9").feature("con1").set("inheritplot", "vol1");
    model.result("pg9").feature("con1").feature().create("filt1", "Filter");
    model.result("pg9").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg9").feature("con1").feature("filt1").set("shownodespec", "on");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg7").run();
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").run();
    model.result("pg10").label("\u6c29\u8d28\u91cf\u5206\u6570");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "plas.wAr");
    model.result("pg10").feature("surf1").set("descr", "\u8d28\u91cf\u5206\u6570");
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").run();
    model.result("pg11").label("\u6fc0\u53d1\u6001\u6c29\u8d28\u91cf\u5206\u6570");
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "plas.wArs");
    model.result("pg11").feature("surf1").set("descr", "\u8d28\u91cf\u5206\u6570");
    model.result("pg11").run();

    model.title("\u70ed\u7b49\u79bb\u5b50\u4f53");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6c42\u89e3\u5728 1 torr \u6c29\u6c14\u73af\u5883\u4e2d\u9ad8\u5bc6\u5ea6\u7535\u611f\u8026\u5408\u7b49\u79bb\u5b50\u4f53\u53cd\u5e94\u5668\u7684\u95ee\u9898\uff0c\u8ba1\u7b97\u4e86\u4e0e\u7b49\u79bb\u5b50\u4f53\u65b9\u7a0b\u5b8c\u5168\u8026\u5408\u7684\u6d41\u4f53\u6d41\u52a8\u548c\u6c14\u4f53\u52a0\u70ed\u8fc7\u7a0b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("thermal_plasma.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

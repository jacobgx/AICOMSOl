/*
 * superheated_steam_drying.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:25 by COMSOL 6.3.0.290. */
public class superheated_steam_drying {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Phase_Change");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("ht", "HeatTransferInMoistAir", "geom1");
    model.component("comp1").physics().create("mt", "MoistureTransportFreePorousMedia", "geom1");

    model.component("comp1").multiphysics().create("mf1", "MoistureFlow", 3);
    model.component("comp1").multiphysics("mf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics().create("ham1", "HeatAndMoisture", 3);
    model.component("comp1").multiphysics("ham1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("ham1").selection().all();
    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/mt", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/mf1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ham1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/nitf1", true);

    model.param().set("dp", "6.2[mm]");
    model.param().descr("dp", "Wood particle diameter");
    model.param().set("L", "3[cm]");
    model.param().descr("L", "Length of drying chamber");
    model.param().set("H", "1[cm]");
    model.param().descr("H", "Height of drying chamber");
    model.param().set("U_in", "0.02[m/s]");
    model.param().descr("U_in", "Inlet velocity");
    model.param().set("T_in", "130[degC]");
    model.param().descr("T_in", "Inlet temperature");
    model.param().set("K0", "1[1/s]");
    model.param().descr("K0", "Evaporation source rate");
    model.param().set("por", "0.65");
    model.param().descr("por", "Porosity");
    model.param().set("sl0", "0.6");
    model.param().descr("sl0", "Initial liquid water saturation");
    model.param().set("kappa", "1e-12[m^2]");
    model.param().descr("kappa", "Permeability");
    model.param().set("Cp_b", "770[J/(kg*K)]");
    model.param().descr("Cp_b", "Dry bulk heat capacity of wood particle");
    model.param().set("rho_b", "740[kg/m^3]");
    model.param().descr("rho_b", "Dry bulk density of wood particle");
    model.param().set("k_eff", "0.14[W/(m*K)]");
    model.param().descr("k_eff", "Effective thermal conductivity");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"L", "H", "H"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "dp/2");
    model.component("comp1").geom("geom1").feature("sph1").set("pos", new String[]{"L/3", "0", "H"});
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("sph1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("blk1");
    model.component("comp1").geom("geom1").feature("par1").set("keeptool", true);
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1", 1);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Xdry", "mt.wc/rho_b");
    model.component("comp1").variable("var1").descr("Xdry", "Moisture content on dry basis");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "wa");
    model.component("comp1").func("an1").set("expr", "if(X>0.256,1,X/0.256*(2-X/0.256))");
    model.component("comp1").func("an1").set("args", "X");
    model.component("comp1").func("an1").set("fununit", "1");
    model.component("comp1").func("an1").setIndex("argunit", 1, 0);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(2, 4, 7, 8);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(1);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(10);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").set(2);

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().named("sel4");

    model.component("comp1").common().create("ampr1", "AmbientProperties");
    model.component("comp1").common("ampr1").set("T_amb", "T_in");
    model.component("comp1").common("ampr1").set("phi_amb", "mt.pA/mt.fpsat(T_in)");

    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").selection().named("sel4");
    model.component("comp1").material("pmat1").set("family", "wood");

    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("EnablePorousMediaDomains", true);
    model.component("comp1").physics("spf").feature("init1").set("u_init", new String[]{"U_in", "0", "0"});
    model.component("comp1").physics("spf").feature("wallbc1").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("sel2");
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "U_in");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("sel3");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("sel1");
    model.component("comp1").physics("spf").create("porous1", "PorousMedium", 3);
    model.component("comp1").physics("spf").feature("porous1").selection().named("sel4");
    model.component("comp1").physics("ht").feature("init1").set("Tinit_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("init2", "init", 3);
    model.component("comp1").physics("ht").feature("init2").selection().named("sel4");
    model.component("comp1").physics("ht").feature("init2").set("Tinit", "100[degC]");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 2);
    model.component("comp1").physics("ht").feature("ifl1").selection().named("sel2");
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().named("sel3");
    model.component("comp1").physics("ht").create("sar1", "SurfaceToAmbientRadiation", 2);
    model.component("comp1").physics("ht").feature("sar1").selection().set(6, 9);
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad", 0.9);
    model.component("comp1").physics("ht").feature("sar1").set("Tamb_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ht").feature("sym1").selection().named("sel1");
    model.component("comp1").physics("ht").create("mporous1", "MoistPorousMediumHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("mporous1").selection().named("sel4");
    model.component("comp1").physics("ht").feature("mporous1")
         .set("EffectiveConductivity", "EquivalentThermalConductivity");
    model.component("comp1").physics("mt").prop("PhysicalModelProperty").set("MixtureType", "ConcentratedSpecies");
    model.component("comp1").physics("mt").feature("hporous1").selection().set(2);
    model.component("comp1").physics("mt").feature("hporous1").set("aw", "wa(Xdry)");
    model.component("comp1").physics("mt").feature("hporous1").set("K_evap", "K0");
    model.component("comp1").physics("mt").feature("hporous1").feature("lw1")
         .set("capillaryConductionModelNonequilibrium", "pressure");
    model.component("comp1").physics("mt").feature("hporous1").feature("lw1").feature("init1")
         .set("phys.porous.lw.slvar_init", "sl0");
    model.component("comp1").physics("mt").feature("hporous1").feature("lw1").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("mt").feature("hporous1").feature("lw1").feature("sym1").selection()
         .named("sel1");
    model.component("comp1").physics("mt").feature("hporous1").feature("ma1").feature("init1").set("phi_init", 1);
    model.component("comp1").physics("mt").feature("hporous1").feature("ma1").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("mt").feature("hporous1").feature("ma1").feature("sym1").selection()
         .named("sel1");
    model.component("comp1").physics("mt").feature("init1").set("phi_init_src", "root.comp1.ampr1.phi_amb");
    model.component("comp1").physics("mt").create("ifl1", "Inflow", 2);
    model.component("comp1").physics("mt").feature("ifl1").selection().named("sel2");
    model.component("comp1").physics("mt").feature("ifl1").set("Tustr_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("mt").feature("ifl1").set("phiustr_src", "root.comp1.ampr1.phi_amb");
    model.component("comp1").physics("mt").create("ofl1", "Outflow", 2);
    model.component("comp1").physics("mt").feature("ofl1").selection().named("sel3");
    model.component("comp1").physics("mt").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("mt").feature("sym1").selection().named("sel1");

    model.component("comp1").material("pmat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k_eff"});
    model.component("comp1").material("pmat1").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"kappa"});
    model.component("comp1").material("pmat1").feature().create("solid1", "Solid", "comp1");
    model.component("comp1").material("pmat1").feature("solid1").set("vfrac", "1-por");
    model.component("comp1").material("pmat1").feature("solid1").propertyGroup("def")
         .set("density", new String[]{"rho_b"});
    model.component("comp1").material("pmat1").feature("solid1").propertyGroup("def")
         .set("heatcapacity", new String[]{"Cp_b"});

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "min");
    model.study("std1").feature("time").set("tlist", "range(0,0.1,1)[s] range(1,10,59)[s] range(1,1,60)");
    model.study("std1").feature("time").set("useparam", true);
    model.study("std1").feature("time").setIndex("pname", "dp", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "m", 0);
    model.study("std1").feature("time").setIndex("pname", "dp", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "m", 0);
    model.study("std1").feature("time").setIndex("pname", "T_in", 0);
    model.study("std1").feature("time").setIndex("plistarr", "130 150 170", 0);
    model.study("std1").feature("time").setIndex("punit", "degC", 0);
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
    model.result().dataset("surf1").selection().set(3, 5);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").setIndex("looplevel", 77, 0);
    model.result("pg2").setIndex("looplevel", 3, 1);
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
    model.result("pg4").label("\u76f8\u5bf9\u6e7f\u5ea6 (mt)");
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("expr", "mt.phi");
    model.result("pg4").feature("mslc1").set("colortable", "Kyanite");
    model.result("pg4").feature("mslc1").set("smooth", "internal");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u58c1\u6e29");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg5").feature("surf1").feature("sel1").selection().set(3, 5);
    model.result("pg5").feature().create("arwv1", "ArrowVolume");
    model.result("pg5").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg5").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg5").feature("arwv1").set("solutionparams", "parent");
    model.result("pg5").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg5").feature("arwv1").set("xnumber", 30);
    model.result("pg5").feature("arwv1").set("ynumber", 30);
    model.result("pg5").feature("arwv1").set("znumber", 30);
    model.result("pg5").feature("arwv1").set("arrowtype", "cone");
    model.result("pg5").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg5").feature("arwv1").set("data", "parent");
    model.result("pg5").feature("arwv1").feature().create("col1", "Color");
    model.result("pg5").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg5").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg5").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg4").run();
    model.result("pg4").feature("mslc1").set("xnumber", "0");
    model.result("pg4").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("mslc1").set("ycoord", 0);
    model.result("pg4").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("mslc1").set("zcoord", "H");
    model.result("pg4").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u9971\u548c (mt)");
    model.result("pg6").feature().create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").set("solutionparams", "parent");
    model.result("pg6").feature("mslc1").set("expr", "mt.sl");
    model.result("pg6").feature("mslc1").set("colortable", "Kyanite");
    model.result("pg6").feature("mslc1").set("smooth", "internal");
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("data", "parent");
    model.result("pg6").label("\u9971\u548c (mt)");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("mslc1").set("xnumber", "0");
    model.result("pg6").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg6").feature("mslc1").set("ycoord", 0);
    model.result("pg6").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("mslc1").set("zcoord", "H");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("paramindicator", "T_in = eval(T_in,\u00b0C)\u00b0C, Time = eval(t,min) min");
    model.result("pg7").set("legendpos", "rightdouble");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "Xdry");
    model.result("pg7").feature("surf1").set("colortable", "Kyanite");
    model.result("pg7").feature("surf1").create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection().set(7, 8);
    model.result("pg7").run();
    model.result("pg7").create("surf2", "Surface");
    model.result("pg7").feature("surf2").set("expr", "T");
    model.result("pg7").feature("surf2").set("colortable", "HeatCameraLight");
    model.result("pg7").feature("surf2").create("sel1", "Selection");
    model.result("pg7").feature("surf2").feature("sel1").selection().set(2, 4);
    model.result("pg7").run();
    model.result("pg7").create("str1", "StreamlineSurface");
    model.result("pg7").feature("str1").selection().set(2, 4);
    model.result("pg7").feature("str1").set("posmethod", "uniform");
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").set("color", "black");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").selection().geom("geom1", 3);
    model.result("pg8").selection().named("sel4");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "mt.G_evap");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").set("titletype", "manual");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").setIndex("expr", "aveop1(Xdry)", 0);
    model.result("pg9").feature("glob1").set("linewidth", 3);
    model.result("pg9").feature("glob1").set("autodescr", false);
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").create("glob1", "Global");
    model.result("pg10").feature("glob1").set("markerpos", "datapoints");
    model.result("pg10").feature("glob1").set("linewidth", "preference");
    model.result("pg10").feature("glob1").setIndex("expr", "aveop1(T)", 0);
    model.result("pg10").feature("glob1").setIndex("unit", "\u00b0C", 0);
    model.result("pg10").feature("glob1").set("linewidth", 3);
    model.result("pg10").feature("glob1").set("autodescr", false);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").set("titletype", "manual");
    model.result("pg11").set("xlabelactive", true);
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").setIndex("expr", "aveop1(mt.phi)", 0);
    model.result("pg11").feature("glob1").set("linewidth", 3);
    model.result("pg11").feature("glob1").set("autodescr", false);
    model.result("pg11").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "mt.massBalance", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "mt.ma1.massBalance", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "mt.hporous1.massBalance", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "mt.hporous1.lw1.massBalance", 3);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "mt.hporous1.ma1.massBalance", 4);
    model.result().evaluationGroup("eg1").run();
    model.result("pg7").run();

    model.title("\u6728\u8d28\u9897\u7c92\u7684\u8fc7\u70ed\u84b8\u6c7d\u5e72\u71e5");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u70ed\u6e7f\u6d41\u52a8\u201d\u7279\u5f81\u6765\u6a21\u62df\u6728\u8d28\u9897\u7c92\u7684\u8fc7\u70ed\u84b8\u6c7d\u5e72\u71e5\u8fc7\u7a0b\uff0c\u5176\u4e2d\u4f7f\u7528\u975e\u5e73\u8861\u516c\u5f0f\u8ba1\u7b97\u6728\u8d28\u9897\u7c92\u4e2d\u6db2\u6001\u6c34\u548c\u6c14\u76f8\u7684\u4f20\u9012\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("superheated_steam_drying.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

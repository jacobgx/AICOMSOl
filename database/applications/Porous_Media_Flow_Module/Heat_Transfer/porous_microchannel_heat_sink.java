/*
 * porous_microchannel_heat_sink.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:13 by COMSOL 6.3.0.290. */
public class porous_microchannel_heat_sink {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Porous_Media_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").set("solnum", "auto");
    model.study("std1").feature("stat2").set("notsolnum", "auto");
    model.study("std1").feature("stat2").set("outputmap", new String[]{});
    model.study("std1").feature("stat2").set("ngenAUX", "1");
    model.study("std1").feature("stat2").set("goalngenAUX", "1");
    model.study("std1").feature("stat2").set("ngenAUX", "1");
    model.study("std1").feature("stat2").set("goalngenAUX", "1");
    model.study("std1").feature("stat2").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat2").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat2").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").feature("stat2").setSolveFor("/physics/spf", false);

    model.component("comp1").geom("geom1").insertFile("porous_microchannel_heat_sink_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("adjsel2");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u6750\u6599\u5c5e\u6027\u548c\u5de5\u4f5c\u6761\u4ef6");
    model.param("par2").set("rho_f", "998[kg/m^3]");
    model.param("par2").descr("rho_f", "\u5bc6\u5ea6\uff0c\u6d41\u4f53");
    model.param("par2").set("mu_f", "8.55e-4[Pa*s]");
    model.param("par2").descr("mu_f", "\u9ecf\u5ea6\uff0c\u6d41\u4f53");
    model.param("par2").set("k_f", "0.6[W/(m*K)]");
    model.param("par2").descr("k_f", "\u5bfc\u70ed\u7cfb\u6570\uff0c\u6d41\u4f53");
    model.param("par2").set("Cp_f", "4182[J/(kg*K)]");
    model.param("par2").descr("Cp_f", "\u70ed\u5bb9\uff0c\u6d41\u4f53");
    model.param("par2").set("por", "0.404");
    model.param("par2").descr("por", "\u5b54\u9699\u7387");
    model.param("par2").set("d_p", "20[um]");
    model.param("par2").descr("d_p", "\u5b54\u9699\u5927\u5c0f");
    model.param("par2").set("kappa", "d_p^2/150*por^3/(1-por)^2");
    model.param("par2").descr("kappa", "\u6e17\u900f\u7387");
    model.param("par2").set("q_in", "50[W/cm^2]");
    model.param("par2").descr("q_in", "\u70ed\u8f7d\u8377");
    model.param("par2").set("T_in", "300[K]");
    model.param("par2").descr("T_in", "\u5165\u53e3\u6e29\u5ea6");
    model.param("par2").set("u_in", "0.2[m/s]");
    model.param("par2").descr("u_in", "\u5165\u53e3\u901f\u5ea6");

    model.material().create("mat1", "Common", "");
    model.material("mat1").label("\u6c34");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat2").label("Steel AISI 4340");
    model.material("mat2").set("family", "steel");
    model.material("mat2").propertyGroup("def").label("Basic");
    model.material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat2").propertyGroup("Enu").set("E", "205[GPa]");
    model.material("mat2").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").set("link", "mat2");
    model.component("comp1").material("matlnk2").selection().named("geom1_sel1");
    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").selection().named("geom1_sel2");

    model.component("comp1").physics("ht").create("porous1", "PorousMediumHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("porous1").selection().named("geom1_sel2");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1")
         .set("porousMatrixPropertiesType", "solidPhaseProperties");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("EnablePorousMediaDomains", true);
    model.component("comp1").physics("spf").selection().named("geom1_unisel1");
    model.component("comp1").physics("spf").create("porous1", "PorousMedium", 3);
    model.component("comp1").physics("spf").feature("porous1").selection().named("geom1_sel2");
    model.component("comp1").physics("spf").feature("porous1").set("flowModelType", "nonDarcian");
    model.component("comp1").physics("spf").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("spf").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kappa", "0", "0", "0", "kappa", "0", "0", "0", "kappa"});
    model.component("comp1").physics("ht").feature("fluid1").selection().named("geom1_unisel1");

    model.component("comp1").material("pmat1").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material("pmat1").feature().create("solid1", "Solid", "comp1");
    model.component("comp1").material("pmat1").feature("solid1").set("link", "mat2");
    model.component("comp1").material("pmat1").feature("solid1").set("vfrac", "1-por");
    model.component("comp1").material("pmat1").set("linkBase", "mat1");
    model.material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"k_f"});
    model.material("mat1").propertyGroup("def").set("density", new String[]{"rho_f"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"Cp_f"});
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu_f"});

    model.component("comp1").physics("ht").create("bhs1", "BoundaryHeatSource", 2);
    model.component("comp1").physics("ht").feature("bhs1").selection().set(3);
    model.component("comp1").physics("ht").feature("bhs1").set("Qb_input", "q_in");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 2);
    model.component("comp1").physics("ht").feature("ifl1").selection().named("geom1_sel4");
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T_in");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().named("geom1_sel5");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ht").feature("sym1").selection().named("geom1_sel3");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("geom1_sel4");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "u_in");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_sel5");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("geom1_sel3");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().named("geom1_sel4");
    model.component("comp1").cpl("aveop1").label("\u5e73\u5747\uff1a\u591a\u5b54 MCHS \u7684\u5165\u53e3");
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop2").selection().set(2);
    model.component("comp1").cpl("aveop2").label("\u5e73\u5747\uff1a\u4e2d\u5fc3\u7ebf\uff0c\u5e95\u9762");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("Dh", "2*width_channel*height_channel/(width_channel+height_channel)", "\u6c34\u529b\u76f4\u5f84");
    model.component("comp1").variable("var1").set("dp", "aveop1(p)", "\u538b\u964d");
    model.component("comp1").variable("var1").set("Tw", "aveop2(T)", "\u5e73\u5747\u58c1\u6e29\u5ea6");
    model.component("comp1").variable("var1").set("Omega", "spf.out1.Mflow/rho_f*dp", "\u6cf5\u9001\u529f\u7387");
    model.component("comp1").variable("var1").set("h_mchs", "q_in/(Tw-T_in)", "\u4f20\u70ed\u7cfb\u6570");
    model.component("comp1").variable("var1").set("Nu", "h_mchs*Dh/k_f", "\u52aa\u585e\u5c14\u6570");
    model.component("comp1").variable("var1").set("Re", "rho_f*u_in*Dh/mu_f", "\u96f7\u8bfa\u6570");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").run("cr1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_sel4");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").feature().remove("ftet1");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("geom1_sel4");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").selection().set(6, 9, 21, 23, 27);
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"spf/porous1"});
    model.study("std1").feature("stat2").set("useadvanceddisable", true);
    model.study("std1").feature("stat2").set("disabledphysics", new String[]{"spf/porous1"});
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"spf/porous1", "ht/porous1"});
    model.study("std1").feature("stat2").set("disabledphysics", new String[]{"spf/porous1", "ht/porous1"});
    model.study("std1").label("\u7814\u7a76 1\uff1a\u53c2\u8003 MCHS");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u901f\u5ea6 (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("slc1", "Slice");
    model.result("pg2").feature("slc1").label("\u5207\u9762");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("expr", "spf.U");
    model.result("pg2").feature("slc1").set("smooth", "internal");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(6, 8, 14, 15, 17);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u538b\u529b (spf)");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "p");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature("surf1").feature().create("tran1", "Transparency");
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
    model.result("pg4").feature("surf1").feature("sel1").selection().set(6, 8, 14, 15, 17);
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("solutionparams", "parent");
    model.result("pg4").feature("vol1").set("expr", "nitf1.T");
    model.result("pg4").feature("vol1").set("smooth", "internal");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result("pg4").feature("vol1").feature().create("sel1", "Selection");
    model.result("pg4").feature("vol1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg4").feature("vol1").feature("sel1").selection().set(1);
    model.result("pg4").feature("vol1").set("inheritplot", "surf1");
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
    model.result("pg4").feature("arwv1").feature("col1").set("expr", "spf.U");
    model.result("pg4").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg4").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();

    model.component("comp1").variable("var1").set("h_ref", "withsol('sol1',h_mchs)");
    model.component("comp1").variable("var1").descr("h_ref", "\u53c2\u8003\u4f20\u70ed\u7cfb\u6570");
    model.component("comp1").variable("var1").set("Omega_ref", "withsol('sol1',Omega)");
    model.component("comp1").variable("var1").descr("Omega_ref", "\u53c2\u8003\u6cf5\u9001\u529f\u7387");
    model.component("comp1").variable("var1").set("FOM", "h_mchs/h_ref/(Omega/Omega_ref)^(1/3)");
    model.component("comp1").variable("var1").descr("FOM", "\u54c1\u8d28\u56e0\u6570");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("plotgroup", "Default");
    model.study("std2").feature("stat").set("solnum", "auto");
    model.study("std2").feature("stat").set("notsolnum", "auto");
    model.study("std2").feature("stat").set("outputmap", new String[]{});
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nitf1", false);
    model.study("std2").create("stat2", "Stationary");
    model.study("std2").feature("stat2").set("plotgroup", "Default");
    model.study("std2").feature("stat2").set("solnum", "auto");
    model.study("std2").feature("stat2").set("notsolnum", "auto");
    model.study("std2").feature("stat2").set("outputmap", new String[]{});
    model.study("std2").feature("stat2").set("ngenAUX", "1");
    model.study("std2").feature("stat2").set("goalngenAUX", "1");
    model.study("std2").feature("stat2").set("ngenAUX", "1");
    model.study("std2").feature("stat2").set("goalngenAUX", "1");
    model.study("std2").feature("stat2").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat2").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat2").setSolveFor("/multiphysics/nitf1", true);
    model.study("std2").feature("stat2").setSolveFor("/physics/spf", false);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u53c2\u6570\u5316");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "Cp_f", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "J/(kg*K)", 0);
    model.study("std2").feature("param").setIndex("pname", "Cp_f", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "J/(kg*K)", 0);
    model.study("std2").feature("param").setIndex("pname", "th_porous", 0);
    model.study("std2").feature("param").setIndex("plistarr", "range(0.05,0.025,0.2)", 0);
    model.study("std2").feature("param").setIndex("punit", "mm", 0);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").study("std2");
    model.sol("sol5").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol5");
    model.batch("p1").run("compute");

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg5").set("data", "dset5");
    model.result("pg5").setIndex("looplevel", 7, 0);
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("solutionparams", "parent");
    model.result("pg5").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("vol1").set("smooth", "internal");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result().dataset("dset5").set("geom", "geom1");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u901f\u5ea6 (spf) 1");
    model.result("pg6").set("data", "dset5");
    model.result("pg6").setIndex("looplevel", 7, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("slc1", "Slice");
    model.result("pg6").feature("slc1").label("\u5207\u9762");
    model.result("pg6").feature("slc1").set("showsolutionparams", "on");
    model.result("pg6").feature("slc1").set("expr", "spf.U");
    model.result("pg6").feature("slc1").set("smooth", "internal");
    model.result("pg6").feature("slc1").set("showsolutionparams", "on");
    model.result("pg6").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").label("\u5916\u58c1 1");
    model.result().dataset("surf2").set("data", "dset5");
    model.result().dataset("surf2").selection().geom("geom1", 2);
    model.result().dataset("surf2").selection().set(6, 8, 14, 15, 17);
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u538b\u529b (spf) 1");
    model.result("pg7").set("data", "surf2");
    model.result("pg7").setIndex("looplevel", 7, 0);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").label("\u8868\u9762");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("expr", "p");
    model.result("pg7").feature("surf1").set("colortable", "Dipole");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result("pg7").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1) 1");
    model.result("pg8").set("data", "dset5");
    model.result("pg8").setIndex("looplevel", 7, 0);
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").label("\u58c1\u6e29");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("solutionparams", "parent");
    model.result("pg8").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg8").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result("pg8").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg8").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg8").feature("surf1").feature("sel1").selection().set(6, 8, 14, 15, 17);
    model.result("pg8").feature().create("vol1", "Volume");
    model.result("pg8").feature("vol1").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg8").feature("vol1").set("showsolutionparams", "on");
    model.result("pg8").feature("vol1").set("solutionparams", "parent");
    model.result("pg8").feature("vol1").set("expr", "nitf1.T");
    model.result("pg8").feature("vol1").set("smooth", "internal");
    model.result("pg8").feature("vol1").set("showsolutionparams", "on");
    model.result("pg8").feature("vol1").set("data", "parent");
    model.result("pg8").feature("vol1").feature().create("sel1", "Selection");
    model.result("pg8").feature("vol1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg8").feature("vol1").feature("sel1").selection().set(1);
    model.result("pg8").feature("vol1").set("inheritplot", "surf1");
    model.result("pg8").feature().create("arwv1", "ArrowVolume");
    model.result("pg8").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg8").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg8").feature("arwv1").set("solutionparams", "parent");
    model.result("pg8").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg8").feature("arwv1").set("xnumber", 30);
    model.result("pg8").feature("arwv1").set("ynumber", 30);
    model.result("pg8").feature("arwv1").set("znumber", 30);
    model.result("pg8").feature("arwv1").set("arrowtype", "cone");
    model.result("pg8").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg8").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg8").feature("arwv1").set("data", "parent");
    model.result("pg8").feature("arwv1").feature().create("col1", "Color");
    model.result("pg8").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg8").feature("arwv1").feature("col1").set("expr", "spf.U");
    model.result("pg8").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg8").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg5").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").label("\u53c2\u8003 MCHS");

    model.result("pg5").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").add("plotgroup", "pg8");
    model.nodeGroup("grp2").label("\u53c2\u6570\u5316 MCHS");

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset5");
    model.result().numerical("gev1").set("expr", new String[]{"dp"});
    model.result().numerical("gev1").set("descr", new String[]{"\u538b\u964d"});
    model.result().numerical("gev1").set("unit", new String[]{"Pa"});
    model.result().numerical("gev1").set("expr", new String[]{"dp", "Omega"});
    model.result().numerical("gev1").set("descr", new String[]{"\u538b\u964d", "\u6cf5\u9001\u529f\u7387"});
    model.result().numerical("gev1").set("expr", new String[]{"dp", "Omega", "h_mchs"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u538b\u964d", "\u6cf5\u9001\u529f\u7387", "\u4f20\u70ed\u7cfb\u6570"});
    model.result().numerical("gev1").set("expr", new String[]{"dp", "Omega", "h_mchs", "Nu"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u538b\u964d", "\u6cf5\u9001\u529f\u7387", "\u4f20\u70ed\u7cfb\u6570", "\u52aa\u585e\u5c14\u6570"});
    model.result().numerical("gev1").set("expr", new String[]{"dp", "Omega", "h_mchs", "Nu", "Re"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u538b\u964d", "\u6cf5\u9001\u529f\u7387", "\u4f20\u70ed\u7cfb\u6570", "\u52aa\u585e\u5c14\u6570", "\u96f7\u8bfa\u6570"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").set("data", "none");
    model.result("pg9").create("tblp1", "Table");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg9").feature("tblp1").set("source", "table");
    model.result("pg9").feature("tblp1").set("table", "tbl1");
    model.result("pg9").feature("tblp1").set("linewidth", "preference");
    model.result("pg9").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg9").run();
    model.result("pg9").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg9").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg9").feature("tblp1").set("legend", true);
    model.result("pg9").feature().duplicate("tblp2", "tblp1");
    model.result("pg9").run();
    model.result("pg9").feature("tblp2").set("plotcolumns", new int[]{4});
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").label("\u4f20\u70ed\u7cfb\u6570\u548c\u538b\u964d");
    model.result("pg9").set("twoyaxes", true);
    model.result("pg9").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg9").set("legendpos", "upperleft");
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u96f7\u8bfa\u6570\u548c\u52aa\u585e\u5c14\u6570");
    model.result("pg10").run();
    model.result("pg10").feature("tblp1").set("plotcolumns", new int[]{6});
    model.result("pg10").run();
    model.result("pg10").feature("tblp2").set("plotcolumns", new int[]{5});
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").set("legendpos", "lowermiddle");
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset5");
    model.result().numerical("gev2").setIndex("expr", "FOM", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").set("data", "none");
    model.result("pg11").create("tblp1", "Table");
    model.result("pg11").feature("tblp1").set("source", "table");
    model.result("pg11").feature("tblp1").set("table", "tbl2");
    model.result("pg11").feature("tblp1").set("linewidth", "preference");
    model.result("pg11").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").set("titletype", "manual");
    model.result("pg11").set("title", "\u54c1\u8d28\u56e0\u6570 (FOM)");
    model.result("pg11").run();
    model.result("pg11").label("FOM");
    model.result("pg6").run();
    model.result("pg6").feature("slc1").set("quickplane", "zx");
    model.result("pg6").run();
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol1").create("sel1", "Selection");
    model.result("pg6").feature("vol1").feature("sel1").selection().named("geom1_sel1");
    model.result("pg6").run();
    model.result("pg6").feature("vol1").create("mtrl1", "MaterialAppearance");
    model.result("pg6").run();
    model.result("pg6").feature("vol1").feature("mtrl1").set("appearance", "custom");
    model.result("pg6").feature("vol1").feature("mtrl1").set("family", "steel");
    model.result("pg6").run();
    model.result("pg6").set("view", "new");
    model.result("pg6").run();

    model.view("view5").camera().set("viewscaletype", "manual");
    model.view("view5").camera().set("xscale", 5);
    model.view("view5").camera().set("zscale", 2);

    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 3, 0);
    model.result("pg6").set("edges", false);
    model.result("pg6").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("data", "dset5");
    model.result().create("pg12", "PlotGroup3D");

    model.nodeGroup("grp2").add("plotgroup", "pg12");

    model.result("pg12").run();
    model.result("pg12").set("data", "mir1");
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg12").feature("surf1").create("sel1", "Selection");
    model.result("pg12").feature("surf1").feature("sel1").selection().named("geom1_difsel1");
    model.result("pg12").feature("surf1").feature("sel1").selection().set(6, 14, 17);
    model.result("pg12").run();
    model.result("pg12").create("strmsl1", "StreamlineMultislice");
    model.result("pg12").feature("strmsl1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg12").feature("strmsl1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg12").feature("strmsl1").set("xnumber", "0");
    model.result("pg12").feature("strmsl1").set("ynumber", "0");
    model.result("pg12").feature("strmsl1").set("posmethod", "magnitude");
    model.result("pg12").feature("strmsl1").set("mdist", new double[]{0.002, 0.01});
    model.result("pg12").feature("strmsl1").set("pointtype", "arrow");
    model.result("pg12").feature("strmsl1").set("arrowtype", "cone");
    model.result("pg12").feature("strmsl1").create("col1", "Color");
    model.result("pg12").run();
    model.result("pg12").feature("strmsl1").feature("col1").set("expr", "p");
    model.result("pg12").feature("strmsl1").feature("col1").set("colortable", "GrayScale");
    model.result("pg12").run();
    model.result("pg12").label("\u901f\u5ea6\u573a\u548c\u6e29\u5ea6\u573a");
    model.result("pg12").setIndex("looplevel", 3, 0);
    model.result("pg12").set("view", "view5");
    model.result("pg12").set("edges", false);
    model.result("pg12").run();

    model.title("\u591a\u5b54\u5fae\u901a\u9053\u6563\u70ed\u5668\u7684\u6027\u80fd");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u591a\u5b54\u5fae\u901a\u9053\u6563\u70ed\u5668\u76f8\u5bf9\u4e8e\u4f20\u7edf\u5fae\u901a\u9053\u6563\u70ed\u5668\u7684\u6709\u6548\u6027\u3002\u8be5\u6a21\u578b\u5df2\u5b8c\u5168\u53c2\u6570\u5316\uff0c\u5176\u4e2d\u901a\u8fc7\u5bf9\u591a\u5b54\u57fa\u677f\u7684\u539a\u5ea6\u8fdb\u884c\u53c2\u6570\u7814\u7a76\u6765\u786e\u5b9a\u6700\u4f73\u6784\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();

    model.label("porous_microchannel_heat_sink.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}

/*
 * rtm_wind_turbine_blade.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class rtm_wind_turbine_blade {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Multiphase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("br", "PorousMediaFlowBrinkman", "geom1");
    model.component("comp1").physics().create("ls", "LevelSetPorousMedia", "geom1");

    model.component("comp1").multiphysics().create("tpf1", "TwoPhaseFlowLevelSet", 3);
    model.component("comp1").multiphysics("tpf1").set("Fluid_physics", "br");
    model.component("comp1").multiphysics("tpf1").set("Mathematics_physics", "ls");
    model.component("comp1").multiphysics("tpf1").selection().all();
    model.component("comp1").multiphysics().create("ww1", "WettedWall", 2);
    model.component("comp1").multiphysics("ww1").set("Fluid_physics", "br");
    model.component("comp1").multiphysics("ww1").set("Mathematics_physics", "ls");

    model.study().create("std1");
    model.study("std1").create("phasei", "PhaseInitialization");
    model.study("std1").feature("phasei").set("ftplistmethod", "manual");
    model.study("std1").feature("phasei").set("solnum", "auto");
    model.study("std1").feature("phasei").set("notsolnum", "auto");
    model.study("std1").feature("phasei").set("outputmap", new String[]{});
    model.study("std1").feature("phasei").set("ngenAUX", "1");
    model.study("std1").feature("phasei").set("goalngenAUX", "1");
    model.study("std1").feature("phasei").set("ngenAUX", "1");
    model.study("std1").feature("phasei").set("goalngenAUX", "1");
    model.study("std1").feature("phasei").setSolveFor("/physics/br", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/ls", true);
    model.study("std1").feature("phasei").setSolveFor("/multiphysics/tpf1", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/br", false);
    model.study("std1").feature("phasei").setSolveFor("/multiphysics/ww1", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/br", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("initstudy", "std1");
    model.study("std1").feature("time").set("notstudy", "std1");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("useinitsol", "on");
    model.study("std1").feature("time").set("notsolmethod", "sol");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/br", true);
    model.study("std1").feature("time").setSolveFor("/physics/ls", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/tpf1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ww1", true);

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").insertFile("rtm_wind_turbine_blade_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("rho_air", "1[kg/m^3]", "\u7a7a\u6c14\u5bc6\u5ea6");
    model.param("par2").set("mu_air", "1e-5[Pa*s]", "\u7a7a\u6c14\u9ecf\u5ea6");
    model.param("par2").set("rho_resin", "1250[kg/m^3]", "\u6811\u8102\u5bc6\u5ea6");
    model.param("par2").set("mu_resin", "0.195[Pa*s]", "\u6811\u8102\u9ecf\u5ea6");
    model.param("par2").set("epsilon_1", "0.45", "\u6750\u6599 1 \u7684\u5b54\u9699\u7387");
    model.param("par2").set("epsilon_2", "0.5", "\u6750\u6599 2 \u7684\u5b54\u9699\u7387");
    model.param("par2").set("epsilon_3", "0.5", "\u6750\u6599 3 \u7684\u5b54\u9699\u7387");
    model.param("par2").set("p_in", "800[kPa]", "\u5165\u53e3\u538b\u529b");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u6811\u8102");
    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").selection().set(1, 8);
    model.component("comp1").material("pmat1").set("porosity", "epsilon_1");
    model.component("comp1").material().create("pmat2", "PorousMedia");
    model.component("comp1").material("pmat2").selection().set(4, 5, 6, 7);
    model.component("comp1").material("pmat2").set("porosity", "epsilon_2");
    model.component("comp1").material().create("pmat3", "PorousMedia");
    model.component("comp1").material("pmat3").selection().set(2, 3);
    model.component("comp1").material("pmat3").set("porosity", "epsilon_3");

    model.component("comp1").physics("br").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("br").feature("inl1").selection().named("geom1_sel1");
    model.component("comp1").physics("br").feature("inl1").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("br").feature("inl1").set("p0", "800[kPa]");
    model.component("comp1").physics("br").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("br").feature("out1").selection().named("geom1_unisel2");
    model.component("comp1").physics("ls").feature("porous1").feature("lsmp1").set("gamma", "2e-4");
    model.component("comp1").physics("ls").feature("porous1").feature("lsmp1").set("epsilon_ls", "2*d_i");
    model.component("comp1").physics("ls").feature("initfluid2").selection().named("geom1_thi2_dom");
    model.component("comp1").physics("ls").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("ls").feature("inl1").selection().named("geom1_sel1");
    model.component("comp1").physics("ls").feature("inl1").set("lscond", "Fluid2ls");
    model.component("comp1").physics("ls").create("out1", "Outlet", 2);
    model.component("comp1").physics("ls").feature("out1").selection().named("geom1_unisel2");

    model.component("comp1").multiphysics("tpf1").set("Fluid1", "mat1");
    model.component("comp1").multiphysics("tpf1").set("Fluid2", "mat2");

    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"1e-5"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1250"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", new String[]{"0.195"});
    model.component("comp1").material("pmat1").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"2.9e-10", "8e-11", "2.9e-10"});
    model.component("comp1").material("pmat2").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"2.5e-10", "7e-11", "2.5e-10"});
    model.component("comp1").material("pmat3").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"1.7e-10", "8e-11", "1.7e-10"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_difsel1");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "1.5*d_i");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_thi2_dom");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "min");
    model.study("std1").feature("time").set("tlist", "range(0,1,60)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");

    model.component("comp1").massProp().create("mass1", "MassProperties");
    model.component("comp1").massProp("mass1").selection().set(1, 2, 3, 4, 8);
    model.component("comp1").massProp("mass1").set("createMass", false);
    model.component("comp1").massProp("mass1").set("createCenterOfMass", false);
    model.component("comp1").massProp("mass1").set("createMomentOfInertia", false);
    model.component("comp1").massProp("mass1").set("createPrincipalInertia", false);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().all();
    model.component("comp1").cpl("intop1").selection().set(1, 2, 3, 4, 8);

    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1")
         .setIndex("stopcondarr", "comp1.intop1(comp1.ls.Vf2)/comp1.mass1.volume > 0.95", 0);
    model.sol("sol1").feature("t1").feature("st1").set("storestopcondsol", "stepafter");
    model.sol("sol1").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (br)");
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
         .set(2, 3, 5, 6, 8, 9, 10, 14, 15, 16, 19, 20, 23, 24, 27, 28, 29, 30, 32, 34, 35, 38, 39, 40, 42, 43, 44, 47, 48, 51, 52);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (br)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").setIndex("looplevel", 46, 0);
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
    model.result("pg3").label("\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570 (ls)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("slc1", "Slice");
    model.result("pg3").feature("slc1").set("expr", "ls.Vf1");
    model.result("pg3").feature("slc1").set("smooth", "internal");
    model.result("pg3").feature("slc1").set("data", "parent");
    model.result("pg3").feature().create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", "ls.Vf1");
    model.result("pg3").feature("iso1").set("levelmethod", "levels");
    model.result("pg3").feature("iso1").set("levels", "0.5");
    model.result("pg3").feature("iso1").set("coloring", "uniform");
    model.result("pg3").feature("iso1").set("colorlegend", false);
    model.result("pg3").feature("iso1").set("color", "gray");
    model.result("pg3").feature("iso1").set("smooth", "none");
    model.result("pg3").feature("iso1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("slc1").active(false);
    model.result("pg1").run();
    model.result("pg1").selection().geom("geom1", 2);
    model.result("pg1").selection().named("geom1_difsel1");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("plotcomp", "tangential");
    model.result("pg1").feature("arws1").set("arrowlength", "normalized");
    model.result("pg1").feature("arws1").set("color", "white");
    model.result("pg1").feature("arws1").set("scaleactive", true);
    model.result("pg1").feature("arws1").set("scale", 80);
    model.result("pg1").run();
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("inheritplot", "surf1");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").feature("slc1").active(false);
    model.result("pg3").run();
    model.result("pg3").feature("iso1").set("expr", "ls.Vf2");
    model.result("pg3").feature("iso1").set("levels", "0 0.5 1");
    model.result("pg3").feature("iso1").set("surfacetype", "filled");
    model.result("pg3").feature("iso1").set("coloring", "colortable");
    model.result("pg3").feature("iso1").set("colortable", "AuroraAustralisDark");
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{16});
    model.result("pg3").label("\u6d41\u4f53 2 \u7684\u4f53\u79ef\u5206\u6570 (ls)");
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u6d41\u4f53 2 \u7684\u4f53\u79ef\u5206\u6570 - \u9635\u5217");
    model.result("pg4").set("plotarrayenable", true);
    model.result("pg4").set("arrayshape", "square");
    model.result("pg4").feature("iso1").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("iso1").set("data", "dset1");
    model.result("pg4").feature("iso1").set("looplevel", new int[]{6});
    model.result("pg4").feature("iso1").set("manualindexing", true);
    model.result("pg4").feature("iso1").set("rowindex", 1);
    model.result("pg4").feature().duplicate("iso2", "iso1");
    model.result("pg4").feature("iso2").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("iso2").set("looplevel", new int[]{11});
    model.result("pg4").feature("iso2").set("colindex", 1);
    model.result("pg4").feature("iso1").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("iso3", "iso1");
    model.result("pg4").feature().duplicate("iso4", "iso2");
    model.result("pg4").feature("iso3").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("iso3").set("looplevel", new int[]{21});
    model.result("pg4").feature("iso3").set("rowindex", 0);
    model.result("pg4").feature("iso4").set("arraydim", "2");
    model.result("pg4").run();
    model.result("pg4").feature("iso4").set("looplevel", new int[]{31});
    model.result("pg4").feature("iso4").set("rowindex", 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("tlan1", "TableAnnotation");
    model.result("pg4").feature("tlan1").set("arraydim", "2");
    model.result("pg4").feature("tlan1").set("source", "localtable");
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 2, 0, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 1.5, 0, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.5, 0, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "t = 5 min", 0, 3);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 9, 1, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 1.5, 1, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.5, 1, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "t = 10 min", 1, 3);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 2, 2, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", -1, 2, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.5, 2, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "t = 20 min", 2, 3);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 9, 3, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", -1, 3, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0.5, 3, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "t = 30 min", 3, 3);
    model.result("pg4").run();
    model.result("pg4").set("titletype", "manual");
    model.result("pg4")
         .set("title", "\u7b49\u503c\u9762\uff1a\u4e0d\u540c\u8f93\u51fa\u65f6\u6b65\u65f6\u6d41\u4f53 2 \u7684\u4f53\u79ef\u5206\u6570");
    model.result("pg4").set("paramindicator", "");
    model.result("pg4").run();

    model.title("\u98ce\u529b\u53d1\u7535\u673a\u53f6\u7247\u7684\u6811\u8102\u4f20\u9012\u6a21\u5851");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u4e24\u76f8\u6d41\uff0c\u6c34\u5e73\u96c6\uff0cBrinkman \u65b9\u7a0b\u201d\u63a5\u53e3\u6a21\u62df\u98ce\u529b\u53d1\u7535\u673a\u53f6\u7247\u7684\u6811\u8102\u4f20\u9012\u6a21\u5851 (RTM) \u5de5\u827a\uff0c\u5176\u4e2d\u5c06\u6811\u8102\u6ce8\u5165\u5230\u7531\u5404\u5411\u5f02\u6027\u6e17\u900f\u7387\u4e0d\u540c\u7684\u5404\u79cd\u590d\u5408\u6750\u6599\u7ec4\u6210\u7684\u9884\u5236\u4ef6\u4e2d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("rtm_wind_turbine_blade.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

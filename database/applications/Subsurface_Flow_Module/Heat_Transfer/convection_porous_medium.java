/*
 * convection_porous_medium.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:30 by COMSOL 6.3.0.290. */
public class convection_porous_medium {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("br", "PorousMediaFlowBrinkman", "geom1");
    model.component("comp1").physics("br").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("br").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("ht", "PorousMediaHeatTransfer", "geom1");
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("dz", "1[m]");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "br");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").feature().create("fluid", "Fluid", "comp1");
    model.component("comp1").material("pmat1").feature().create("solid", "Solid", "comp1");
    model.component("comp1").material("pmat1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/br", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rho0", "1000[kg/m^3]", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("mu0", "0.001[Pa*s]", "\u6d41\u4f53\u9ecf\u5ea6");
    model.param().set("alphap", "1e-6[1/K]", "\u6d41\u4f53\u7684\u4f53\u79ef\u70ed\u81a8\u80c0\u7cfb\u6570");
    model.param().set("k0", "6[W/(m*K)]", "\u6d41\u4f53\u70ed\u5bfc\u7387");
    model.param().set("gamma", "1", "\u6d41\u4f53\u6bd4\u70ed\u6bd4");
    model.param().set("Cp0", "4200[J/(kg*K)]", "\u6d41\u4f53\u6052\u538b\u70ed\u5bb9");
    model.param().set("epsilon", "0.4", "\u5b54\u9699\u7387");
    model.param().set("kappa", "1e-3[m^2]", "\u6e17\u900f\u7387");
    model.param().set("p0", "1[atm]", "\u53c2\u8003\u538b\u529b");
    model.param().set("Tc", "20[degC]", "\u53c2\u8003\u6e29\u5ea6");
    model.param().set("Th", "42[degC]", "\u9ad8\u6e29");
    model.param().set("L", "0.1[m]", "\u957f\u5ea6\u5c3a\u5ea6");
    model.param().set("Pr", "mu0*Cp0/k0", "\u666e\u6717\u7279\u6570");
    model.param().set("Ra", "Cp0*rho0^2*g_const*alphap*(Th-Tc)*L^3/(k0*mu0)", "\u745e\u5229\u6570");

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", "L");
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "L", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "L/10", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material("pmat1").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"kappa"});
    model.component("comp1").material("pmat1").feature("fluid").propertyGroup("def")
         .set("density", new String[]{"rho0"});
    model.component("comp1").material("pmat1").feature("fluid").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k0"});
    model.component("comp1").material("pmat1").feature("fluid").propertyGroup("def")
         .set("heatcapacity", new String[]{"Cp0"});
    model.component("comp1").material("pmat1").feature("fluid").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"mu0"});
    model.component("comp1").material("pmat1").feature("solid").set("vfrac", "1-epsilon");
    model.component("comp1").material("pmat1").feature("solid").propertyGroup("def")
         .set("density", new String[]{"0"});
    model.component("comp1").material("pmat1").feature("solid").propertyGroup("def")
         .set("heatcapacity", new String[]{"0"});
    model.component("comp1").material("pmat1").feature("solid").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0"});

    model.component("comp1").physics("br").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("br").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("br").prop("AdvancedSettingProperty").set("PseudoTimeSetting", "Off");
    model.component("comp1").physics("br").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("br").feature("prpc1").selection().set(4);
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", 2);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "Tc");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(2);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "Th");
    model.component("comp1").physics("ht").create("temp2", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp2").selection().set(3, 5);
    model.component("comp1").physics("ht").feature("temp2").set("T0", "Tc");
    model.component("comp1").physics("ht").create("temp3", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp3").selection().set(1);
    model.component("comp1").physics("ht").feature("temp3").set("T0", "Th-(Th-Tc)*s");
    model.component("comp1").physics("ht").create("temp4", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp4").selection().set(4);
    model.component("comp1").physics("ht").feature("temp4").set("T0", "Tc-(Tc-Th)*s");

    model.component("comp1").multiphysics("nitf1").set("BoussinesqApproximation", true);
    model.component("comp1").multiphysics("nitf1").set("SpecifyDensity", "CustomLinearizedDensity");
    model.component("comp1").multiphysics("nitf1").set("rhoref", "rho0");
    model.component("comp1").multiphysics("nitf1").set("alphap", "alphap");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "rho0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "kg/m^3", 0);
    model.study("std1").feature("stat").setIndex("pname", "rho0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "kg/m^3", 0);
    model.study("std1").feature("stat").setIndex("pname", "alphap", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "10^{range(-12,1,-6)}", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (br)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (br)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "nitf1.T");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg4").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg4").feature().create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg4").feature("arws1").set("showsolutionparams", "on");
    model.result("pg4").feature("arws1").set("solutionparams", "parent");
    model.result("pg4").feature("arws1").set("expr", new String[]{"nitf1.ux", "nitf1.uy"});
    model.result("pg4").feature("arws1").set("xnumber", 30);
    model.result("pg4").feature("arws1").set("ynumber", 30);
    model.result("pg4").feature("arws1").set("arrowtype", "cone");
    model.result("pg4").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("arws1").set("showsolutionparams", "on");
    model.result("pg4").feature("arws1").set("data", "parent");
    model.result("pg4").feature("arws1").feature().create("col1", "Color");
    model.result("pg4").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg4").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg4").feature("arws1").feature("filt1").set("expr", "br.U>nitf1.Uave");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("resolution", "finer");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();

    model.title("\u591a\u5b54\u4ecb\u8d28\u4e2d\u7684\u81ea\u7136\u5bf9\u6d41");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u591a\u5b54\u4ecb\u8d28\u4e2d\u7684\u5bf9\u6d41\u548c\u4f20\u5bfc\uff0c\u5176\u4e2d\u7684\u6d41\u4f53\u6d41\u52a8\u5b8c\u5168\u7531\u6e29\u5ea6\u4ea7\u751f\u7684\u5bc6\u5ea6\u5dee\u5f02\u9a71\u52a8\u7684\u6d6e\u529b\u5f15\u8d77\u3002\u7ed3\u679c\u4e0e\u5df2\u53d1\u5e03\u7684\u7814\u7a76\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.label("convection_porous_medium.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

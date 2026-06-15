/*
 * semi_infinite_wall.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:38 by COMSOL 6.3.0.290. */
public class semi_infinite_wall {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransferInBuildingMaterials", "geom1");
    model.component("comp1").physics().create("mt", "MoistureTransportInBuildingMaterials", "geom1");

    model.component("comp1").multiphysics().create("ham1", "HeatAndMoisture", 1);
    model.component("comp1").multiphysics("ham1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("ham1").set("Moist_physics", "mt");
    model.component("comp1").multiphysics("ham1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/mt", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ham1", true);

    model.param().set("T_e", "30[degC]");
    model.param().descr("T_e", "\u5916\u90e8\u6e29\u5ea6");
    model.param().set("phi_e", "0.95");
    model.param().descr("phi_e", "\u5916\u90e8\u76f8\u5bf9\u6e7f\u5ea6");
    model.param().set("T_i", "20[degC]");
    model.param().descr("T_i", "\u5185\u90e8\u6e29\u5ea6");
    model.param().set("phi_i", "0.5");
    model.param().descr("phi_i", "\u5185\u90e8\u76f8\u5bf9\u6e7f\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 20, 1);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u6750\u6599 - Norm 15026");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("relativehumidity");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("\u89e3\u6790\uff1awc");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "wc");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "146/(1+(-8e-8*462*293.15*1000*log(phi))^1.6)^0.375");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", "phi");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("\u89e3\u6790\uff1ak");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "k");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "1.5+15.8e-3*w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", "w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").setIndex("plotargs", 150, 0, 2);
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").label("\u89e3\u6790\uff1as");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("expr", "0.125e8*((146/w)^(1/0.375)-1)^0.625");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", "w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").setIndex("plotargs", 150, 0, 2);
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an4", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("an4").label("\u89e3\u6790\uff1aK");
    model.component("comp1").material("mat1").propertyGroup("def").func("an4").set("funcname", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an4")
         .set("expr", "exp(-39.2619+0.0704*(w-146/2)-1.7420e-4*(w-146/2)^2-2.7953e-6*(w-146/2)^3-1.1566e-7*(w-146/2)^4+2.5969e-9*(w-146/2)^5)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an4").set("args", "w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an4").setIndex("plotargs", 150, 0, 2);
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an5", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("an5").label("\u89e3\u6790\uff1aDw");
    model.component("comp1").material("mat1").propertyGroup("def").func("an5").set("funcname", "Dw");
    model.component("comp1").material("mat1").propertyGroup("def").func("an5").set("expr", "-d(s(w),w)*K(w)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an5").set("args", "w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an5").setIndex("plotargs", 150, 0, 2);
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an6", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("an6").label("\u89e3\u6790\uff1adelta_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an6").set("funcname", "delta_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an6")
         .set("expr", "(0.01801528/R_const/293.15*26.1e-6/200*(1-w/146)/((1-0.497)*(1-w/146)^2+0.497))");
    model.component("comp1").material("mat1").propertyGroup("def").func("an6").set("args", "w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an6").setIndex("plotargs", 150, 0, 2);
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(wc(phi))"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"2145.88"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"850"});
    model.component("comp1").material("mat1").propertyGroup("def").set("diffusion", new String[]{"Dw(wc(phi))"});
    model.component("comp1").material("mat1").propertyGroup("def").set("watercontent", new String[]{"wc(phi)"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("vaporpermeability", new String[]{"delta_p(wc(phi))"});

    model.component("comp1").func().create("rm1", "Ramp");
    model.component("comp1").func("rm1").set("location", 100);
    model.component("comp1").func("rm1").set("slope", "1e-3");
    model.component("comp1").func("rm1").set("cutoffactive", true);
    model.component("comp1").func("rm1").set("smoothzonelocactive", true);
    model.component("comp1").func("rm1").set("smoothzonecutoffactive", true);
    model.component("comp1").func("rm1").set("smoothzoneloc", 100);
    model.component("comp1").func("rm1").set("smoothzonecutoff", 100);

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_i");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 0);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_i+(T_e-T_i)*rm1(t[1/s])");
    model.component("comp1").physics("ht").create("temp2", "TemperatureBoundary", 0);
    model.component("comp1").physics("ht").feature("temp2").selection().set(2);
    model.component("comp1").physics("ht").feature("temp2").set("T0", "T_i");
    model.component("comp1").physics("mt").feature("init1").set("phi_init", "phi_i");
    model.component("comp1").physics("mt").create("mc1", "MoistureContent", 0);
    model.component("comp1").physics("mt").feature("mc1").selection().set(1);
    model.component("comp1").physics("mt").feature("mc1").set("T0", "T_i+(T_e-T_i)*rm1(t[1/s])");
    model.component("comp1").physics("mt").feature("mc1").set("phi0", "phi_i+(phi_e-phi_i)*rm1(t[1/s])");
    model.component("comp1").physics("mt").create("mc2", "MoistureContent", 0);
    model.component("comp1").physics("mt").feature("mc2").selection().set(2);
    model.component("comp1").physics("mt").feature("mc2").set("T0", "T_i");
    model.component("comp1").physics("mt").feature("mc2").set("phi0", "phi_i");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 1000);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 25);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "d");
    model.study("std1").feature("time").set("tlist", "range(0,1,365)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1e-3");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg1").feature().create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").set("data", "parent");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u76f8\u5bf9\u6e7f\u5ea6 (mt)");
    model.result("pg2").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg2").feature().create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("solutionparams", "parent");
    model.result("pg2").feature("lngr1").set("expr", "mt.phi");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").set("smooth", "internal");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("data", "parent");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg1").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").importData("semi_infinite_wall_temperature.txt");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").importData("semi_infinite_wall_moisture_storage_function.txt");
    model.result("pg1").run();
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0);
    model.result("pg1").set("xmax", 6);
    model.result("pg1").set("ymin", 19);
    model.result("pg1").set("ymax", 30);
    model.result("pg1").setIndex("looplevelinput", "manual", 0);
    model.result("pg1").setIndex("looplevel", new int[]{8, 31, 366}, 0);
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").set("unit", "\u00b0C");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").feature("tblp1").set("linestyle", "none");
    model.result("pg1").feature("tblp1").set("linecolor", "fromtheme");
    model.result("pg1").feature("tblp1").set("linemarker", "asterisk");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u84c4\u6c34\u91cf\u51fd\u6570");
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 0);
    model.result("pg2").set("xmax", 0.11);
    model.result("pg2").set("ymin", 25);
    model.result("pg2").set("ymax", 130);
    model.result("pg2").setIndex("looplevelinput", "manual", 0);
    model.result("pg2").setIndex("looplevel", new int[]{8, 31, 366}, 0);
    model.result("pg2").run();
    model.result("pg2").feature("lngr1").selection().all();
    model.result("pg2").feature("lngr1").set("expr", "mt.wc");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("table", "tbl2");
    model.result("pg2").feature("tblp1").set("linestyle", "none");
    model.result("pg2").feature("tblp1").set("linecolor", "fromtheme");
    model.result("pg2").feature("tblp1").set("linemarker", "asterisk");
    model.result("pg2").run();

    model.title("\u534a\u65e0\u9650\u58c1\u4e2d\u7684\u70ed\u6e7f\u4f20\u9012");

    model
         .description("\u672c\u6559\u7a0b\u4ecb\u7ecd\u5982\u4f55\u6a21\u62df\u5efa\u7b51\u6784\u4ef6\u4e2d\u7684\u8026\u5408\u70ed\u6e7f\u4f20\u9012\u3002\u8fd9\u4e2a\u4e00\u7ef4\u6a21\u578b\u662f EN 15026 \u89c4\u8303\u4e2d\u5b9a\u4e49\u7684\u57fa\u51c6\u6d4b\u8bd5\uff0c\u7528\u4e8e\u901a\u8fc7\u6570\u503c\u4eff\u771f\u6765\u9a8c\u8bc1\u8f6f\u4ef6\u662f\u5426\u9002\u7528\u4e8e\u8bc4\u4f30\u6c34\u5206\u8fc1\u79fb\u3002\u60a8\u53ef\u4ee5\u9a8c\u8bc1\u4f7f\u7528 COMSOL Multiphysics \u5f97\u5230\u7684\u6570\u503c\u7ed3\u679c\u662f\u5426\u5728\u89c4\u8303\u4e2d\u6307\u5b9a\u7684\u6709\u6548\u6027\u8303\u56f4\u5185\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("semi_infinite_wall.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

/*
 * single_cylinder_reciprocating_engine.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:46 by COMSOL 6.3.0.290. */
public class single_cylinder_reciprocating_engine {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Rotordynamics_Module\\Automotive_and_Aerospace");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");
    model.component("comp1").physics().create("hdb", "HydrodynamicBearing", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);
    model.study("std1").feature("time").setSolveFor("/physics/hdb", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "single_cylinder_reciprocating_engine.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("C", "2e-5[m]");
    model.param().descr("C", "\u8f74\u627f\u95f4\u9699");
    model.param().set("mu0", "0.072[Pa*s]");
    model.param().descr("mu0", "\u6da6\u6ed1\u6cb9\u9ecf\u5ea6");
    model.param().set("t1", "0.025[s]");
    model.param().descr("t1", "\u521a\u5ea6\u6298\u51cf\u8d77\u70b9");
    model.param().set("t2", "0.04[s]");
    model.param().descr("t2", "\u521a\u5ea6\u6298\u51cf\u7ec8\u70b9");
    model.param().set("kb", "1e9[N/m]");
    model.param().descr("kb", "\u8f74\u627f\u521a\u5ea6");
    model.param().set("theta0", "240[deg]");
    model.param().descr("theta0", "\u66f2\u67c4\u521d\u59cb\u65cb\u8f6c");

    model.nodeGroup().create("grp1", "Definitions", "comp1");
    model.nodeGroup("grp1").set("type", "pair");
    model.nodeGroup("grp1").add("pair", "ap1");
    model.nodeGroup("grp1").add("pair", "ap2");
    model.nodeGroup("grp1").add("pair", "ap3");
    model.nodeGroup("grp1").add("pair", "ap5");
    model.nodeGroup("grp1").label("\u94f0\u94fe\u5173\u8282\u5bf9");

    model.component("comp1").pair("ap4").label("\u68f1\u67f1\u5173\u8282\u5bf9");
    model.component("comp1").pair("ap4").active(false);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u8f74\u9888 1");
    model.component("comp1").selection("sel1").set(16, 17, 18, 19);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("\u57fa\u5ea7 1");
    model.component("comp1").selection("sel2").set(147, 148, 149, 150);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").label("\u8f74\u9888 2");
    model.component("comp1").selection("sel3").set(60, 61, 62, 63);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").label("\u57fa\u5ea7 2");
    model.component("comp1").selection("sel4").set(269, 270, 271, 272);
    model.component("comp1").selection().create("cyl1", "Cylinder");
    model.component("comp1").selection("cyl1").label("\u6d3b\u585e\u5934");
    model.component("comp1").selection("cyl1").set("entitydim", 2);
    model.component("comp1").selection("cyl1").set("r", 0.042);
    model.component("comp1").selection("cyl1").set("top", 0.208);
    model.component("comp1").selection("cyl1").set("bottom", 0.196);
    model.component("comp1").selection("cyl1").set("condition", "inside");

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom(3);
    model.component("comp1").view("view1").hideEntities("hide1").add(6);
    model.component("comp1").view("view1").hideEntities("hide1").add(8);
    model.component("comp1").view("view1").hideEntities("hide1").add(9);
    model.component("comp1").view("view1").set("hidestatus", "showonlyhidden");

    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u8f74\u9888");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel3"});
    model.component("comp1").selection().duplicate("uni2", "uni1");
    model.component("comp1").selection("uni2").label("\u57fa\u5ea7");
    model.component("comp1").selection("uni2").set("input", new String[]{"sel2", "sel4"});
    model.component("comp1").selection().duplicate("uni3", "uni2");
    model.component("comp1").selection("uni3").label("\u8f74\u627f\u7cfb\u7edf");
    model.component("comp1").selection("uni3").set("input", new String[]{"uni1", "uni2"});
    model.component("comp1").selection().create("cyl2", "Cylinder");
    model.component("comp1").selection("cyl2").label("\u56fa\u5b9a 1");
    model.component("comp1").selection("cyl2").set("entitydim", 2);
    model.component("comp1").selection("cyl2").set("r", 0.006);
    model.component("comp1").selection("cyl2").set("pos", new double[]{-0.035, -0.0514, 0});
    model.component("comp1").selection("cyl2").set("condition", "inside");
    model.component("comp1").selection().duplicate("cyl3", "cyl2");
    model.component("comp1").selection("cyl3").label("\u56fa\u5b9a 2");
    model.component("comp1").selection("cyl3").set("pos", new double[]{-0.035, 0.0514, 0});
    model.component("comp1").selection().duplicate("cyl4", "cyl3");
    model.component("comp1").selection("cyl4").label("\u56fa\u5b9a 3");
    model.component("comp1").selection("cyl4").set("pos", new double[]{0.035, 0.0514, 0});
    model.component("comp1").selection().duplicate("cyl5", "cyl4");
    model.component("comp1").selection("cyl5").label("\u56fa\u5b9a 4");
    model.component("comp1").selection("cyl5").set("pos", new double[]{0.035, -0.0514, 0});
    model.component("comp1").selection().create("uni4", "Union");
    model.component("comp1").selection("uni4").label("\u56fa\u5b9a");
    model.component("comp1").selection("uni4").set("entitydim", 2);
    model.component("comp1").selection("uni4").set("input", new String[]{"cyl2", "cyl3", "cyl4", "cyl5"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("cyl1");
    model.component("comp1").cpl("intop1").set("frame", "material");
    model.component("comp1").cpl("intop1").label("\u5bf9\u6d3b\u585e\u9876\u6c42\u79ef\u5206");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", "3*pi");
    model.component("comp1").func("step1").set("smooth", "pi/18");
    model.component("comp1").func("step1").label("\u6b65\u9aa4\uff1a\u8f7d\u8377\u626d\u77e9\u542f\u52a8");
    model.component("comp1").func().duplicate("step2", "step1");
    model.component("comp1").func("step2").label("\u6b65\u9aa4\uff1a\u542f\u52a8\u626d\u77e9\u622a\u65ad");
    model.component("comp1").func("step2").set("location", "2*pi");
    model.component("comp1").func("step2").set("from", 1);
    model.component("comp1").func("step2").set("to", 0);
    model.component("comp1").func("step2").set("smooth", "pi/36");
    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("funcname", "pressure");
    model.component("comp1").func("int1").label("\u63d2\u503c\uff1a\u538b\u529b");
    model.component("comp1").func("int1")
         .set("table", new String[][]{{"0", "0.333341"}, 
         {"0.062832", "0.333676"}, 
         {"0.125664", "0.334685"}, 
         {"0.188496", "0.336374"}, 
         {"0.251327", "0.338773"}, 
         {"0.314159", "0.341890"}, 
         {"0.376991", "0.345720"}, 
         {"0.439823", "0.350424"}, 
         {"0.502655", "0.356064"}, 
         {"0.565487", "0.362558"}, 
         {"0.628319", "0.369907"}, 
         {"0.69115", "0.378112"}, 
         {"0.753982", "0.387171"}, 
         {"0.816814", "0.397386"}, 
         {"0.879646", "0.409931"}, 
         {"0.942478", "0.423785"}, 
         {"1.00531", "0.438949"}, 
         {"1.068142", "0.455424"}, 
         {"1.130973", "0.473208"}, 
         {"1.193805", "0.493791"}, 
         {"1.256637", "0.518365"}, 
         {"1.319469", "0.545340"}, 
         {"1.382301", "0.574716"}, 
         {"1.445133", "0.606494"}, 
         {"1.507964", "0.644267"}, 
         {"1.570796", "0.688175"}, 
         {"1.633628", "0.736555"}, 
         {"1.69646", "0.789407"}, 
         {"1.759292", "0.852604"}, 
         {"1.822124", "0.926066"}, 
         {"1.884956", "1.007690"}, 
         {"1.947787", "1.102102"}, 
         {"2.010619", "1.215645"}, 
         {"2.073451", "1.343468"}, 
         {"2.136283", "1.495750"}, 
         {"2.199115", "1.675395"}, 
         {"2.261947", "1.882994"}, 
         {"2.324779", "2.135523"}, 
         {"2.38761", "2.426274"}, 
         {"2.450442", "2.774202"}, 
         {"2.513274", "3.174684"}, 
         {"2.576106", "3.648949"}, 
         {"2.638938", "4.191032"}, 
         {"2.70177", "4.809170"}, 
         {"2.764602", "5.494002"}, 
         {"2.827433", "6.222693"}, 
         {"2.890265", "6.958928"}, 
         {"2.953097", "7.624993"}, 
         {"3.015929", "8.159523"}, 
         {"3.078761", "8.602927"}, 
         {"3.141593", "14.499959"}, 
         {"3.204425", "20.273087"}, 
         {"3.267256", "22.978690"}, 
         {"3.330088", "21.471803"}, 
         {"3.39292", "19.559541"}, 
         {"3.455752", "17.533486"}, 
         {"3.518584", "15.552903"}, 
         {"3.581416", "13.616194"}, 
         {"3.644247", "11.891606"}, 
         {"3.707079", "10.388861"}, 
         {"3.769911", "9.08177"}, 
         {"3.832743", "7.961185"}, 
         {"3.895575", "7.023701"}, 
         {"3.958407", "6.210162"}, 
         {"4.021239", "5.530754"}, 
         {"4.08407", "4.943275"}, 
         {"4.146902", "4.444838"}, 
         {"4.209734", "4.014451"}, 
         {"4.272566", "3.644175"}, 
         {"4.335398", "3.330475"}, 
         {"4.39823", "3.052088"}, 
         {"4.461062", "2.812026"}, 
         {"4.523893", "2.604513"}, 
         {"4.586725", "2.421240"}, 
         {"4.649557", "2.260050"}, 
         {"4.712389", "2.118594"}, 
         {"4.775221", "1.995081"}, 
         {"4.838053", "1.883758"}, 
         {"4.900885", "1.785708"}, 
         {"4.963716", "1.700390"}, 
         {"5.026548", "1.621991"}, 
         {"5.08938", "1.552126"}, 
         {"5.152212", "1.490794"}, 
         {"5.215044", "1.434858"}, 
         {"5.277876", "1.384069"}, 
         {"5.340708", "1.339536"}, 
         {"5.403539", "1.298929"}, 
         {"5.466371", "1.263264"}, 
         {"5.529203", "1.231980"}, 
         {"5.592035", "1.203333"}, 
         {"5.654867", "1.178288"}, 
         {"5.717699", "1.156319"}, 
         {"5.78053", "1.137162"}, 
         {"5.843362", "1.121068"}, 
         {"5.906194", "1.107072"}, 
         {"5.969026", "1.095461"}, 
         {"6.031858", "1.086239"}, 
         {"6.09469", "1.079022"}, 
         {"6.157522", "1.074008"}, 
         {"6.220353", "1.071081"}, 
         {"6.283185", "1.070139"}});
    model.component("comp1").func("int1").setIndex("argunit", "rad", 0);
    model.component("comp1").func("int1").setIndex("fununit", "bar", 0);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");

    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 3);

    model.component("comp1").view("view1").set("hidestatus", "ignore");

    model.component("comp1").physics("mbd").feature("rd1").selection().set(2, 3, 4, 5);
    model.component("comp1").physics("mbd").feature("rd1").label("\u521a\u6027\u6750\u6599\uff1a\u6c14\u7f38");
    model.component("comp1").physics("mbd").feature("rd1").create("fix1", "FixedConstraint", -1);
    model.component("comp1").physics("mbd").create("rd2", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd2").selection().set(7);
    model.component("comp1").physics("mbd").feature("rd2").label("\u521a\u6027\u6750\u6599\uff1a\u6d3b\u585e");
    model.component("comp1").physics("mbd").create("rd3", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd3").selection().set(8);
    model.component("comp1").physics("mbd").feature("rd3").label("\u521a\u6027\u6750\u6599\uff1a\u8fde\u6746");
    model.component("comp1").physics("mbd").prop("AutoModeling").set("PlnBnd", "none");
    model.component("comp1").physics("mbd").prop("AutoModeling").set("SprBnd", "none");
    model.component("comp1").physics("mbd").prop("AutoModeling").runCommand("createJoints");
    model.component("comp1").physics("mbd").feature("att1").label("\u8fde\u63a5\u4ef6\uff1a\u8f74\u9888 1");
    model.component("comp1").physics("mbd").feature("att2").label("\u8fde\u63a5\u4ef6\uff1a\u57fa\u5ea7 1");
    model.component("comp1").physics("mbd").feature("hgj1").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("hgj1").feature("je1")
         .set("k_u", new String[]{"kb*(1-(t-t1)/(t2-t1)*(t>t1))*(t<=t2)", "0", "0", "0", "kb*(1-(t-t1)/(t2-t1)*(t>t1))*(t<=t2)", "0", "0", "0", "kb*(1-(t-t1)/(t2-t1)*(t>t1))*(t<=t2)"});
    model.component("comp1").physics("mbd").feature("att3").label("\u8fde\u63a5\u4ef6\uff1a\u66f2\u67c4\u9500");
    model.component("comp1").physics("mbd").feature("att4").label("\u8fde\u63a5\u4ef6\uff1a\u8f74\u9888 2");
    model.component("comp1").physics("mbd").feature("att5").label("\u8fde\u63a5\u4ef6\uff1a\u57fa\u5ea7 2");
    model.component("comp1").physics("mbd").feature("hgj3").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("hgj3").feature("je1")
         .set("k_u", new String[]{"kb*(1-(t-t1)/(t2-t1)*(t>t1))*(t<=t2)", "0", "0", "0", "kb*(1-(t-t1)/(t2-t1)*(t>t1))*(t<=t2)", "0", "0", "0", "kb*(1-(t-t1)/(t2-t1)*(t>t1))*(t<=t2)"});

    model.nodeGroup("grp1").active(false);

    model.component("comp1").pair("ap4").active(true);

    model.component("comp1").physics("mbd").prop("AutoModeling").set("CylBnd", "PrismaticJoint");
    model.component("comp1").physics("mbd").prop("AutoModeling").runCommand("createJoints");
    model.component("comp1").physics("mbd").create("fix1", "Fixed", 2);
    model.component("comp1").physics("mbd").feature("fix1").selection().named("uni4");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("theta", "abs(mbd.hgj1.th)");
    model.component("comp1").variable("var1").descr("theta", "\u8f74\u65cb\u8f6c");
    model.component("comp1").variable("var1").set("p", "pressure(mod(theta+theta0,2*pi))");
    model.component("comp1").variable("var1").descr("p", "\u6c14\u7f38\u538b\u529b");
    model.component("comp1").variable("var1").set("Ti", "100[N*m]*step2(theta)");
    model.component("comp1").variable("var1").descr("Ti", "\u542f\u52a8\u626d\u77e9");
    model.component("comp1").variable("var1").set("To", "0.05[N*m*s/rad]*d(theta,t)*step1(theta)");
    model.component("comp1").variable("var1").descr("To", "\u8f93\u51fa\u626d\u77e9");
    model.component("comp1").variable("var1").set("A", "intop1(root.nZ)");
    model.component("comp1").variable("var1").descr("A", "\u6d3b\u585e\u6295\u5f71\u9762\u79ef");
    model.component("comp1").variable("var1").set("P", "-p*A*mbd.prj1.u_t/746[W]");
    model.component("comp1").variable("var1").descr("P", "\u4ea7\u751f\u7684\u529f\u7387");
    model.component("comp1").variable("var1").set("BHP", "To*d(theta,t)/746[W]");
    model.component("comp1").variable("var1").descr("BHP", "\u5236\u52a8\u529b");

    model.component("comp1").physics("mbd").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("mbd").feature("bndl1").selection().named("cyl1");
    model.component("comp1").physics("mbd").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("mbd").feature("bndl1").set("pressure", "p");
    model.component("comp1").physics("mbd").create("rig1", "RigidConnector", 2);

    model.component("comp1").view("view1").set("hidestatus", "hide");

    model.component("comp1").physics("mbd").feature("rig1").selection().set(78);
    model.component("comp1").physics("mbd").feature("rig1").create("rm1", "RigidBodyMoment", -1);
    model.component("comp1").physics("mbd").feature("rig1").feature("rm1").set("Mt", new String[]{"Ti", "0", "0"});
    model.component("comp1").physics("mbd").feature("rig1").feature().duplicate("rm2", "rm1");
    model.component("comp1").physics("mbd").feature("rig1").feature("rm2").set("Mt", new String[]{"-To", "0", "0"});
    model.component("comp1").physics("mbd").prop("Results").set("ReferenceFrame", "att1");
    model.component("comp1").physics("hdb").selection().named("uni1");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("hdb").prop("EquationType")
         .set("EquationType", "ReynoldsEquationWithCavitation");
    model.component("comp1").physics("hdb").feature("hjb1").set("C_plain", "C");
    model.component("comp1").physics("hdb").feature("hjb1").set("BearingCenterType", "fromGeom");
    model.component("comp1").physics("hdb").feature("hjb1").set("mure_mat", "userdef");
    model.component("comp1").physics("hdb").feature("hjb1").set("mure", "mu0");
    model.component("comp1").physics("hdb").feature("hjb1").set("rho_c", "866[kg/m^3]");
    model.component("comp1").physics("hdb").feature().duplicate("hjb2", "hjb1");
    model.component("comp1").physics("hdb").feature("hjb2").selection().named("sel3");

    model.component("comp1").multiphysics().create("sbco1", "SolidBearingCoupling", 2);
    model.component("comp1").multiphysics("sbco1").selection().named("sel1");
    model.component("comp1").multiphysics("sbco1").set("includeFoundation", true);
    model.component("comp1").multiphysics("sbco1").selection("Foundation").named("sel2");
    model.component("comp1").multiphysics().duplicate("sbco2", "sbco1");
    model.component("comp1").multiphysics("sbco2").selection().named("sel3");
    model.component("comp1").multiphysics("sbco2").selection("Foundation").named("sel4");

    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(1);
    model.component("comp1").selection("sel5").label("\u8f74\u627f\u5916\u90e8\u8fb9");
    model.component("comp1").selection("sel5")
         .set(33, 34, 36, 38, 41, 42, 44, 46, 145, 146, 148, 150, 153, 154, 156, 158);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").geom(1);
    model.component("comp1").selection("sel6").set(314, 666);
    model.component("comp1").selection("sel6").set("groupcontang", true);
    model.component("comp1").selection("sel6").label("\u57fa\u5ea7\u5916\u90e8\u8fb9");
    model.component("comp1").selection().create("uni5", "Union");
    model.component("comp1").selection("uni5").set("entitydim", 1);
    model.component("comp1").selection("uni5").set("input", new String[]{"sel5", "sel6"});
    model.component("comp1").selection("uni5").label("\u8f74\u627f\u7cfb\u7edf\u5916\u90e8\u8fb9");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("uni3");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(39, 151);

    model.component("comp1").view("view1").set("hidestatus", "showonlyhidden");

    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(39, 151, 321, 631);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().named("uni5");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 12);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);

    model.component("comp1").view("view1").set("hidestatus", "hide");

    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(2, 3, 4, 5);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(8);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,5e-5,0.12)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "once");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 10);
    model.sol("sol1").feature("t1").feature("fc1").set("termonres", "auto");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u901f\u5ea6 (mbd)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").label("\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("descractive", true);
    model.result("pg2").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("vol1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("rangecoloractive", "on");
    model.result("pg2").feature("vol1").set("rangecolormin", -0.5);
    model.result("pg2").feature("vol1").set("rangecolormax", 9.5);
    model.result("pg2").feature("vol1").set("colortable", "Cyclic");
    model.result("pg2").feature("vol1").set("colorlegend", false);
    model.result("pg2").feature("vol1").set("colortabletype", "discrete");
    model.result("pg2").feature("vol1").set("smooth", "none");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature("vol1").feature().create("def1", "Deform");
    model.result("pg2").feature("vol1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg2").feature("arwl1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6d41\u4f53\u538b\u529b (hdb)");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "pfilm");
    model.result("pg3").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("con1", "Contour");
    model.result("pg3").feature("con1").set("expr", "pfilm");
    model.result("pg3").feature("con1").set("levelrounding", false);
    model.result("pg3").feature("con1").set("colorlegend", false);
    model.result("pg3").feature("con1").set("smooth", "internal");
    model.result("pg3").feature("con1").set("inherittubescale", false);
    model.result("pg3").feature("con1").set("data", "parent");
    model.result("pg3").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").label("\u7814\u7a76 1/\u89e3 1\uff1a\u6c14\u7f38");

    model.component("comp1").view("view1").set("hidestatus", "hide");

    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().set(2, 3, 4, 5);
    model.result().dataset().duplicate("dset3", "dset2");
    model.result().dataset("dset3").label("\u7814\u7a76 1/\u89e3 1\uff1a\u65e0\u6c14\u7f38\u53d1\u52a8\u673a");
    model.result().dataset("dset3").selection().geom("geom1", 3);
    model.result().dataset("dset3").selection().set();
    model.result().dataset("dset3").selection().geom("geom1", 3);
    model.result().dataset("dset3").selection().set(1, 6, 7, 8, 9);
    model.result("pg1").run();
    model.result("pg1").set("data", "dset3");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("data", "dset2");
    model.result("pg1").feature("surf2").set("solutionparams", "parent");
    model.result("pg1").feature("surf2").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("mtrl1").set("usematerialsel", false);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 2401, 0);
    model.result("pg4").label("\u8fb9\u754c\u8f7d\u8377 (mbd)");
    model.result("pg4").set("showlegends", true);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg4").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").feature("surf1").set("inheritcolor", false);
    model.result("pg4").feature("surf1").set("inheritrange", false);
    model.result("pg4").feature("surf1").set("inherittransparency", false);
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def").set("scale", 1);
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg4").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286);
    model.result("pg4").feature("surf1").create("tran1", "Transparency");
    model.result("pg4").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1")
         .set("expr", new String[]{"mbd.bndl1.fax", "mbd.bndl1.fay", "mbd.bndl1.faz"});
    model.result("pg4").feature("arws1").set("placement", "gausspoints");
    model.result("pg4").feature("arws1").set("arrowbase", "head");
    model.result("pg4").feature("arws1").label("\u8fb9\u754c\u8f7d\u8377 1");
    model.result("pg4").feature("arws1").set("inheritplot", "none");
    model.result("pg4").feature("arws1").create("col", "Color");
    model.result("pg4").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg4").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg4").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg4").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg4").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg4").feature("arws1").feature("col").set("topcolor", "red");
    model.result("pg4").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg4").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg4").feature("arws1").set("color", "red");
    model.result("pg4").feature("arws1").create("def", "Deform");
    model.result("pg4").feature("arws1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("arws1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("arws1").feature("def").set("scale", 1);
    model.result("pg4").feature().move("surf1", 1);
    model.result("pg4").label("\u8fb9\u754c\u8f7d\u8377 (mbd)");

    model.nodeGroup().create("dset1mbdlgrp", "Results");
    model.nodeGroup("dset1mbdlgrp").label("\u5916\u52a0\u8f7d\u8377 (mbd)");
    model.nodeGroup("dset1mbdlgrp").set("type", "plotgroup");
    model.nodeGroup("dset1mbdlgrp").label("\u5916\u52a0\u8f7d\u8377 (mbd)");
    model.nodeGroup("dset1mbdlgrp").placeAfter("plotgroup", "pg4");

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 2401, 0);
    model.result("pg5").label("\u5168\u5c40\u529b\u77e9 (mbd)");
    model.result("pg5").set("showlegends", true);
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg5").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "gray");
    model.result("pg5").feature("surf1").set("inheritcolor", false);
    model.result("pg5").feature("surf1").set("inheritrange", false);
    model.result("pg5").feature("surf1").set("inherittransparency", false);
    model.result("pg5").feature("surf1").create("def", "Deform");
    model.result("pg5").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg5").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg5").feature("surf1").feature("def").set("scale", 1);
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg5").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286);
    model.result("pg5").feature("surf1").create("tran1", "Transparency");
    model.result("pg5").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg5").create("pttraj1", "PointTrajectories");
    model.result("pg5").feature("pttraj1").set("plotdata", "global");
    model.result("pg5").feature("pttraj1")
         .set("expr", new String[]{"mbd.rig1.rm1.momposx", "mbd.rig1.rm1.momposy", "mbd.rig1.rm1.momposz"});
    model.result("pg5").feature("pttraj1").set("pointtype", "arrow");
    model.result("pg5").feature("pttraj1")
         .set("arrowexpr", new String[]{"mbd.rig1.rm1.Mx", "mbd.rig1.rm1.My", "mbd.rig1.rm1.Mz"});
    model.result("pg5").feature("pttraj1").set("linetype", "none");
    model.result("pg5").feature("pttraj1").set("arrowtype", "double");
    model.result("pg5").feature("pttraj1").set("arrowbase", "tail");
    model.result("pg5").feature("pttraj1").label("\u4f5c\u7528\u529b\u77e9 1 (\u521a\u6027\u8fde\u63a5\u4ef6 1)");
    model.result("pg5").feature("pttraj1").set("inheritplot", "none");
    model.result("pg5").feature("pttraj1").create("col", "Color");
    model.result("pg5").feature("pttraj1").feature("col").set("colortable", "Rainbow");
    model.result("pg5").feature("pttraj1").feature("col").set("colortabletrans", "none");
    model.result("pg5").feature("pttraj1").feature("col").set("colorscalemode", "linear");
    model.result("pg5").feature("pttraj1").feature("col").set("colordata", "arrowlength");
    model.result("pg5").feature("pttraj1").feature("col").set("coloring", "gradient");
    model.result("pg5").feature("pttraj1").feature("col").set("topcolor", "red");
    model.result("pg5").feature("pttraj1").feature("col").set("bottomcolor", "custom");
    model.result("pg5").feature("pttraj1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg5").feature("pttraj1").set("pointcolor", "blue");
    model.result("pg5").feature().move("surf1", 1);
    model.result("pg5").create("pttraj2", "PointTrajectories");
    model.result("pg5").feature("pttraj2").set("plotdata", "global");
    model.result("pg5").feature("pttraj2")
         .set("expr", new String[]{"mbd.rig1.rm2.momposx", "mbd.rig1.rm2.momposy", "mbd.rig1.rm2.momposz"});
    model.result("pg5").feature("pttraj2").set("pointtype", "arrow");
    model.result("pg5").feature("pttraj2")
         .set("arrowexpr", new String[]{"mbd.rig1.rm2.Mx", "mbd.rig1.rm2.My", "mbd.rig1.rm2.Mz"});
    model.result("pg5").feature("pttraj2").set("linetype", "none");
    model.result("pg5").feature("pttraj2").set("arrowtype", "double");
    model.result("pg5").feature("pttraj2").set("arrowbase", "tail");
    model.result("pg5").feature("pttraj2").label("\u4f5c\u7528\u529b\u77e9 2 (\u521a\u6027\u8fde\u63a5\u4ef6 1)");
    model.result("pg5").feature("pttraj2").set("inheritplot", "pttraj1");
    model.result("pg5").feature("pttraj2").create("col", "Color");
    model.result("pg5").feature("pttraj2").feature("col").set("colortable", "Rainbow");
    model.result("pg5").feature("pttraj2").feature("col").set("colortabletrans", "none");
    model.result("pg5").feature("pttraj2").feature("col").set("colorscalemode", "linear");
    model.result("pg5").feature("pttraj2").feature("col").set("colordata", "arrowlength");
    model.result("pg5").feature("pttraj2").feature("col").set("coloring", "gradient");
    model.result("pg5").feature("pttraj2").feature("col").set("topcolor", "red");
    model.result("pg5").feature("pttraj2").feature("col").set("bottomcolor", "custom");
    model.result("pg5").feature("pttraj2").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg5").feature("pttraj2").set("pointcolor", "blue");
    model.result("pg5").feature().move("surf1", 2);
    model.result("pg5").label("\u5168\u5c40\u529b\u77e9 (mbd)");

    model.nodeGroup("dset1mbdlgrp").add("plotgroup", "pg4");
    model.nodeGroup("dset1mbdlgrp").add("plotgroup", "pg5");

    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg1").feature().copy("arws1", "pg4/arws1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "rightdouble");
    model.result("pg1").run();
    model.result().dataset().duplicate("dset4", "dset1");
    model.result().dataset("dset4").label("\u7814\u7a76 1/\u89e3 1\uff1a\u8f74\u627f");
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().named("uni1");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset4");
    model.result("pg3").set("edges", false);
    model.result("pg3").set("view", "new");
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("expr", new String[]{"hdb.fbx", "hdb.fby", "w"});
    model.result("pg3").feature("arws1").setIndex("expr", "hdb.fbz", 2);
    model.result("pg3").feature("arws1").set("placement", "elements");
    model.result("pg3").feature("arws1").set("scaleactive", true);
    model.result("pg3").feature("arws1").set("scale", "1e-8");
    model.result("pg3").run();
    model.result().dataset().duplicate("dset5", "dset1");
    model.result().dataset("dset5").label("\u7814\u7a76 1/\u89e3 1\uff1a\u66f2\u8f74");
    model.result().dataset("dset5").selection().geom("geom1", 3);
    model.result().dataset("dset5").selection().geom("geom1", 3);
    model.result().dataset("dset5").selection().set(1);
    model.result().dataset().duplicate("dset6", "dset5");
    model.result().dataset("dset6").label("\u7814\u7a76 1/\u89e3 1\uff1a\u57fa\u5ea7");
    model.result().dataset("dset6").selection().geom("geom1", 3);
    model.result().dataset("dset6").selection().set();

    model.component("comp1").view("view1").set("hidestatus", "showonlyhidden");

    model.result().dataset("dset6").selection().geom("geom1", 3);
    model.result().dataset("dset6").selection().set(6, 9);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u66f2\u8f74\u5e94\u529b");
    model.result("pg6").set("data", "dset5");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "mbd.mises");
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").feature("surf1").set("rangecoloractive", true);
    model.result("pg6").feature("surf1").set("rangecolormax", "1e7");
    model.result("pg6").feature("surf1").create("def1", "Deform");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("def1").set("expr", new String[]{"u_ref", "v_ref", "w_ref"});
    model.result("pg6").feature("surf1").feature("def1")
         .set("descr", "\u4f4d\u79fb\u573a\uff0c\u53c2\u8003\u5750\u6807\u7cfb \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg6").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg6").feature("surf1").feature("def1").set("scale", 800);
    model.result("pg6").run();
    model.result("pg6").set("view", "new");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").set("data", "dset6");
    model.result("pg7").label("\u57fa\u5ea7\u5e94\u529b (mbd)");
    model.result("pg7").set("view", "new");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("rangecolormax", "3e6");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").feature("def1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg7").feature("surf1").feature("def1").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg7").feature("surf1").feature("def1").set("scale", "3e3");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u53d1\u52a8\u673a\u8f6c\u901f");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "d(theta,t)*60[s]/(2*pi[rad])", 0);
    model.result("pg8").feature("glob1").setIndex("unit", "RPM", 0);
    model.result("pg8").feature("glob1").setIndex("descr", "\u53d1\u52a8\u673a\u8f6c\u901f", 0);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u53d1\u52a8\u673a\u8f6c\u901f\uff08\u8f6c/\u5206\u949f\uff09");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("showlegends", false);
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u8f74\u627f\u53cd\u4f5c\u7528\u529b\u548c\u6d3b\u585e\u8f7d\u8377");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").set("expr", new String[]{"hdb.hjb1.Fjz"});
    model.result("pg9").feature("glob1")
         .set("descr", new String[]{"\u8f74\u9888\u4e0a\u7684\u6d41\u4f53\u8f7d\u8377\uff0cz \u5206\u91cf"});
    model.result("pg9").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg9").feature("glob1").set("expr", new String[]{"hdb.hjb1.Fjz", "hdb.hjb2.Fjz"});
    model.result("pg9").feature("glob1")
         .set("descr", new String[]{"\u8f74\u9888\u4e0a\u7684\u6d41\u4f53\u8f7d\u8377\uff0cz \u5206\u91cf", "\u8f74\u9888\u4e0a\u7684\u6d41\u4f53\u8f7d\u8377\uff0cz \u5206\u91cf"});
    model.result("pg9").feature("glob1").set("expr", new String[]{"hdb.hjb1.Fjz", "hdb.hjb2.Fjz", "mbd.hgj1.F_elz"});
    model.result("pg9").feature("glob1")
         .set("descr", new String[]{"\u8f74\u9888\u4e0a\u7684\u6d41\u4f53\u8f7d\u8377\uff0cz \u5206\u91cf", "\u8f74\u9888\u4e0a\u7684\u6d41\u4f53\u8f7d\u8377\uff0cz \u5206\u91cf", "\u5173\u8282\u529b\uff08\u5f39\u6027\uff09\uff0cz \u5206\u91cf"});
    model.result("pg9").feature("glob1")
         .set("expr", new String[]{"hdb.hjb1.Fjz", "hdb.hjb2.Fjz", "mbd.hgj1.F_elz", "mbd.hgj3.F_elz"});
    model.result("pg9").feature("glob1")
         .set("descr", new String[]{"\u8f74\u9888\u4e0a\u7684\u6d41\u4f53\u8f7d\u8377\uff0cz \u5206\u91cf", "\u8f74\u9888\u4e0a\u7684\u6d41\u4f53\u8f7d\u8377\uff0cz \u5206\u91cf", "\u5173\u8282\u529b\uff08\u5f39\u6027\uff09\uff0cz \u5206\u91cf", "\u5173\u8282\u529b\uff08\u5f39\u6027\uff09\uff0cz \u5206\u91cf"});
    model.result("pg9").feature("glob1").setIndex("unit", "N", 0);
    model.result("pg9").feature("glob1").setIndex("descr", "\u8f74\u627f\uff08\u5de6\uff09", 0);
    model.result("pg9").feature("glob1").setIndex("unit", "N", 1);
    model.result("pg9").feature("glob1").setIndex("descr", "\u8f74\u627f\uff08\u53f3\uff09", 1);
    model.result("pg9").feature("glob1").setIndex("unit", "N", 2);
    model.result("pg9").feature("glob1").setIndex("descr", "\u5f39\u6027\u5173\u8282\uff08\u5de6\uff09", 2);
    model.result("pg9").feature("glob1").setIndex("unit", "N", 3);
    model.result("pg9").feature("glob1").setIndex("descr", "\u5f39\u6027\u5173\u8282\uff08\u53f3\uff09", 3);
    model.result("pg9").feature("glob1").setIndex("expr", "p*A", 4);
    model.result("pg9").feature("glob1").setIndex("unit", "N", 4);
    model.result("pg9").feature("glob1").setIndex("descr", "\u6d3b\u585e\u8f7d\u8377", 4);
    model.result("pg9").feature("glob1")
         .setIndex("expr", "hdb.hjb1.Fjz+hdb.hjb2.Fjz+mbd.hgj1.F_elz+mbd.hgj3.F_elz", 5);
    model.result("pg9").feature("glob1").setIndex("unit", "N", 5);
    model.result("pg9").feature("glob1").setIndex("descr", "\u603b\u8ba1", 5);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").set("titletype", "label");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u529b (N)");
    model.result("pg9").set("legendpos", "upperleft");
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u66f2\u8f74\u626d\u77e9");
    model.result("pg10").set("titletype", "label");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "\u626d\u77e9 (N*m)");
    model.result("pg10").create("glob1", "Global");
    model.result("pg10").feature("glob1").set("markerpos", "datapoints");
    model.result("pg10").feature("glob1").set("linewidth", "preference");
    model.result("pg10").feature("glob1").setIndex("expr", "-p*A*sin(theta+theta0)*0.02[m]", 0);
    model.result("pg10").feature("glob1").setIndex("unit", "N*m", 0);
    model.result("pg10").feature("glob1")
         .setIndex("descr", "\u66f2\u8f74\u4e0a\u7684\u626d\u77e9\uff08\u8fd1\u4f3c\u503c\uff09", 0);
    model.result("pg10").feature("glob1").setIndex("expr", "Ti", 1);
    model.result("pg10").feature("glob1").setIndex("unit", "N*m", 1);
    model.result("pg10").feature("glob1").setIndex("descr", "\u542f\u52a8\u626d\u77e9", 1);
    model.result("pg10").feature("glob1").setIndex("expr", "To", 2);
    model.result("pg10").feature("glob1").setIndex("unit", "N*m", 2);
    model.result("pg10").feature("glob1").setIndex("descr", "\u8f93\u51fa\u626d\u77e9", 2);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u4ea7\u751f\u7684\u529f\u7387");
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg11").feature("glob1").setIndex("expr", "P", 0);
    model.result("pg11").feature("glob1").setIndex("unit", "HP", 0);
    model.result("pg11").feature("glob1").setIndex("descr", "\u4ea7\u751f\u7684\u529f\u7387", 0);
    model.result("pg11").run();
    model.result("pg11").feature("glob1").set("legend", false);
    model.result("pg11").run();
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("ylabel", "\u4ea7\u751f\u7684\u529f\u7387 (HP)");
    model.result("pg11").set("titletype", "label");
    model.result().duplicate("pg12", "pg11");
    model.result("pg12").run();
    model.result("pg12").label("BHP");
    model.result("pg12").set("ylabel", "BHP");
    model.result("pg12").run();
    model.result("pg12").feature("glob1").setIndex("expr", "BHP", 0);
    model.result("pg12").feature("glob1").setIndex("unit", "HP", 0);
    model.result("pg12").feature("glob1").setIndex("descr", "\u5236\u52a8\u529b", 0);
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").run();
    model.result("pg13").create("glob1", "Global");
    model.result("pg13").feature("glob1").set("markerpos", "datapoints");
    model.result("pg13").feature("glob1").set("linewidth", "preference");
    model.result("pg13").feature("glob1").set("expr", new String[]{"mbd.att1.w"});
    model.result("pg13").feature("glob1").set("descr", new String[]{"\u521a\u4f53\u4f4d\u79fb\uff0cz \u5206\u91cf"});
    model.result("pg13").feature("glob1").set("unit", new String[]{"m"});
    model.result("pg13").feature("glob1").setIndex("expr", "mbd.att1.w/C", 0);
    model.result("pg13").feature("glob1").setIndex("unit", 1, 0);
    model.result("pg13").feature("glob1").setIndex("descr", "", 0);
    model.result("pg13").feature("glob1").set("xdata", "expr");
    model.result("pg13").feature("glob1").set("xdataexpr", "mbd.att1.v");
    model.result("pg13").feature("glob1").set("xdatadescr", "\u521a\u4f53\u4f4d\u79fb\uff0cy \u5206\u91cf");
    model.result("pg13").feature("glob1").set("xdataexpr", "mbd.att1.v/C");
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").label("\u8f74\u9888 1 \u8f68\u9053");
    model.result("pg13").set("titletype", "label");
    model.result("pg13").set("showlegends", false);
    model.result("pg13").set("xlabelactive", true);
    model.result("pg13").set("xlabel", "\u76f8\u5bf9 y \u4f4d\u79fb");
    model.result("pg13").set("ylabelactive", true);
    model.result("pg13").set("ylabel", "\u76f8\u5bf9 z \u4f4d\u79fb");
    model.result("pg13").run();
    model.result().duplicate("pg14", "pg13");
    model.result("pg14").run();
    model.result("pg14").label("\u8f74\u9888\u504f\u5fc3\u7387");
    model.result("pg14").set("xlabelactive", false);
    model.result("pg14").set("ylabel", "\u76f8\u5bf9\u504f\u5fc3\u7387");
    model.result("pg14").run();
    model.result("pg14").feature("glob1").setIndex("expr", "sqrt(mbd.att1.v^2+mbd.att1.w^2)/C", 0);
    model.result("pg14").feature("glob1").setIndex("unit", 1, 0);
    model.result("pg14").feature("glob1").setIndex("descr", "\u76f8\u5bf9\u504f\u5fc3\u7387", 0);
    model.result("pg14").feature("glob1").set("xdata", "solution");
    model.result("pg14").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").label("\u52a8\u753b\uff1a\u4f4d\u79fb");
    model.result().export("anim1").set("looplevelinput", "manualindices");
    model.result().export("anim1").set("looplevelindices", "range(1000,1,1201)");
    model.result().export("anim1").set("maxframes", 100);
    model.result().export("anim1").run();
    model.result("pg3").run();
    model.result().export().create("anim2", "Animation");
    model.result().export("anim2").set("target", "player");
    model.result().export("anim2").set("fontsize", "9");
    model.result().export("anim2").set("colortheme", "globaltheme");
    model.result().export("anim2").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim2").set("background", "color");
    model.result().export("anim2").set("gltfincludelines", "on");
    model.result().export("anim2").set("title1d", "on");
    model.result().export("anim2").set("legend1d", "on");
    model.result().export("anim2").set("logo1d", "on");
    model.result().export("anim2").set("options1d", "on");
    model.result().export("anim2").set("title2d", "on");
    model.result().export("anim2").set("legend2d", "on");
    model.result().export("anim2").set("logo2d", "on");
    model.result().export("anim2").set("options2d", "off");
    model.result().export("anim2").set("title3d", "on");
    model.result().export("anim2").set("legend3d", "on");
    model.result().export("anim2").set("logo3d", "on");
    model.result().export("anim2").set("options3d", "off");
    model.result().export("anim2").set("axisorientation", "on");
    model.result().export("anim2").set("grid", "on");
    model.result().export("anim2").set("axes1d", "on");
    model.result().export("anim2").set("axes2d", "on");
    model.result().export("anim2").set("showgrid", "on");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").label("\u52a8\u753b\uff1a\u6d41\u4f53\u538b\u529b");
    model.result().export("anim2").set("plotgroup", "pg3");
    model.result().export("anim2").set("maxframes", 100);
    model.result().export("anim2").set("looplevelinput", "manualindices");
    model.result().export("anim2").set("looplevelindices", "range(700,1,801)");
    model.result().export("anim2").run();
    model.result().export().create("anim3", "Animation");
    model.result().export("anim3").set("target", "player");
    model.result().export("anim3").set("fontsize", "9");
    model.result().export("anim3").set("colortheme", "globaltheme");
    model.result().export("anim3").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim3").set("background", "color");
    model.result().export("anim3").set("gltfincludelines", "on");
    model.result().export("anim3").set("title1d", "on");
    model.result().export("anim3").set("legend1d", "on");
    model.result().export("anim3").set("logo1d", "on");
    model.result().export("anim3").set("options1d", "on");
    model.result().export("anim3").set("title2d", "on");
    model.result().export("anim3").set("legend2d", "on");
    model.result().export("anim3").set("logo2d", "on");
    model.result().export("anim3").set("options2d", "off");
    model.result().export("anim3").set("title3d", "on");
    model.result().export("anim3").set("legend3d", "on");
    model.result().export("anim3").set("logo3d", "on");
    model.result().export("anim3").set("options3d", "off");
    model.result().export("anim3").set("axisorientation", "on");
    model.result().export("anim3").set("grid", "on");
    model.result().export("anim3").set("axes1d", "on");
    model.result().export("anim3").set("axes2d", "on");
    model.result().export("anim3").set("showgrid", "on");
    model.result().export("anim3").showFrame();
    model.result().export("anim3").label("\u52a8\u753b\uff1a\u57fa\u5ea7\u5e94\u529b");
    model.result().export("anim3").set("plotgroup", "pg7");
    model.result().export("anim3").set("looplevelinput", "manualindices");
    model.result().export("anim3").set("looplevelindices", "range(1100,1,1200)");
    model.result().export("anim3").run();

    model.nodeGroup("grp1").active(true);

    model.result("pg1").run();

    model.title("\u91c7\u7528\u6db2\u4f53\u52a8\u538b\u8f74\u627f\u7684\u5f80\u590d\u5f0f\u53d1\u52a8\u673a");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u7531\u6db2\u4f53\u52a8\u538b\u8f74\u627f\u652f\u6491\u7684\u5355\u7f38\u5f80\u590d\u5f0f\u53d1\u52a8\u673a\u3002\u901a\u8fc7\u5bf9\u53d1\u52a8\u673a\u65bd\u52a0\u542f\u52a8\u8f6c\u77e9\uff0c\u4f7f\u5176\u8fbe\u5230\u6240\u9700\u7684\u8f6c\u901f\u3002\u4e00\u65e6\u53d1\u52a8\u673a\u52a0\u901f\uff0c\u5c31\u4f1a\u4ea7\u751f\u8d1f\u8f7d\u8f6c\u77e9\u3002\u542f\u52a8\u540e\uff0c\u53d1\u52a8\u673a\u4f1a\u5728\u6c14\u7f38\u538b\u529b\u7684\u9a71\u52a8\u4e0b\u81ea\u884c\u8fd0\u8f6c\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u201c\u591a\u4f53\u52a8\u529b\u5b66\u6a21\u5757\u201d\u4e2d\u7684\u201c\u591a\u4f53\u52a8\u529b\u5b66\u201d\u63a5\u53e3\u5bf9\u53d1\u52a8\u673a\u603b\u6210\u8fdb\u884c\u5efa\u6a21\uff0c\u4f7f\u7528\u201c\u8f6c\u5b50\u52a8\u529b\u5b66\u6a21\u5757\u201d\u4e2d\u7684\u201c\u6db2\u4f53\u52a8\u538b\u8f74\u627f\u201d\u63a5\u53e3\u5bf9\u8f74\u627f\u5efa\u6a21\uff0c\u5e76\u4f7f\u7528\u201c\u5b9e\u4f53-\u8f74\u627f\u8026\u5408\u201d\u591a\u7269\u7406\u573a\u8026\u5408\u5c06\u8fd9\u4e24\u4e2a\u63a5\u53e3\u8fdb\u884c\u7ec4\u5408\u3002\n\n\u4eff\u771f\u7ed3\u679c\u5305\u62ec\u66f2\u8f74\u548c\u57fa\u7840\u4e2d\u7684\u5e94\u529b\u3001\u8f74\u627f\u7684\u538b\u529b\u3001\u53d1\u52a8\u673a\u901f\u5ea6\u53d8\u5316\u3001\u53d1\u7535\u529f\u7387\u3001\u5236\u52a8\u9a6c\u529b\u4ee5\u53ca\u8f74\u627f\u4e2d\u8f74\u7684\u8f68\u9053\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("single_cylinder_reciprocating_engine.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}

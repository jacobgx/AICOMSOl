/*
 * positive_column_2d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:03 by COMSOL 6.3.0.290. */
public class positive_column_2d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Direct_Current_Discharges");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("plas", "ColdPlasma", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/plas", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.05, 0.4});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"0.0375", "6e-3"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0, 0.01});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"0.0375", "6e-3"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{0, 0.384});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("co1", "Compose");
    model.component("comp1").geom("geom1").feature("co1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("co1").set("formula", "r1-r2-r3");
    model.component("comp1").geom("geom1").run("co1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new double[]{0, 0.02});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new double[]{0.0375, 0.02});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 7);
    model.component("comp1").geom("geom1").run("mce1");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("mueN", "1E25[1/(m*V*s)]");
    model.component("comp1").variable("var1").descr("mueN", "\u7ea6\u5316\u7535\u5b50\u8fc1\u79fb\u7387");
    model.component("comp1").variable("var1").set("V0", "125[V]");
    model.component("comp1").variable("var1").descr("V0", "\u5916\u52a0\u7535\u538b");
    model.component("comp1").variable("var1").set("Wf", "5");
    model.component("comp1").variable("var1").descr("Wf", "\u529f\u51fd\u6570");
    model.component("comp1").variable("var1").set("p0", "0.5[torr]");
    model.component("comp1").variable("var1").descr("p0", "\u6c14\u538b");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u9634\u6781");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(3, 5, 10);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u9633\u6781");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(6, 8, 11);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u58c1");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(2, 9, 12);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u6240\u6709\u58c1");
    model.component("comp1").selection("sel4").geom(1);
    model.component("comp1").selection("sel4").set(2, 3, 5, 6, 8, 9, 10, 11, 12);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u975e\u9634\u6781\u58c1");
    model.component("comp1").selection("sel5").geom(1);
    model.component("comp1").selection("sel5").set(2, 6, 8, 9, 11, 12);

    model.component("comp1").physics("plas").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("plas").feature("xsec1").set("Filepath", "Ar_xsecs.txt");
    model.component("comp1").physics("plas").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("plas").prop("ElectronProperties").set("ReducedProps", true);
    model.component("comp1").physics("plas").feature("pes1").set("pA", "p0");
    model.component("comp1").physics("plas").feature("pes1")
         .set("muN", new String[]{"mueN", "0", "0", "0", "mueN", "0", "0", "0", "mueN"});
    model.component("comp1").physics("plas").feature("eir2").set("SpecifyReactionUsing", "UseLookupTable");
    model.component("comp1").physics("plas").feature("eir2").set("RateConstantForm", "UseTownsend");
    model.component("comp1").physics("plas").feature("eir2")
         .set("xtownratedata", new double[]{0, 0.1, 0.1059869818882502, 0.11233240329780274, 0.11905772393787836, 0.12618568830660204, 0.13374040261108217, 0.1417474162926805, 0.1502338084331859, 0.1592282793341092, 0.16876124757881478, 0.1788649529057435, 0.1895735652406376, 0.20092330025650468, 0.2129525418521362, 0.22570197196339203, 0.2392147081462638, 0.25353644939701114, 0.26871563070252286, 0.2848035868435802, 0.3018547260049922, 0.31992671377973836, 0.3390806681894053, 0.35938136638046275, 0.38089746369540706, 0.40370172585965547, 0.42787127506942646, 0.4534878508128582, 0.480638086306439, 0.509413801481638, 0.5399123135125903, 0.5722367659350217, 0.60649647746946, 0.6428073117284321, 0.6812920690579614, 0.7220809018385463, 0.7653117546501339, 0.8111308307896871, 0.8596930867190791, 0.9111627561154891, 0.9657139052966048, 1.0235310218990263, 1.0848096388007427, 1.1497569953977356, 1.2185927384710877, 1.2915496650148839, 1.3688745095370807, 1.4508287784959395, 1.537689634694013, 1.6297508346206442, 1.7273237219129889, 1.8307382802953678, 1.9403442495579148, 2.0565123083486516, 2.1796353277791214, 2.3101297000831593, 2.448436746822227, 2.595024211399736, 2.7503878409319458, 2.9150530628251756, 3.089576761729402, 3.2745491628777286, 3.4705958281810667, 3.6783797718286326, 3.898603702549072, 4.132012400115337, 4.379395234130494, 4.641588833612779, 4.919479916408218, 5.214008287999684, 5.52617001985409, 5.857020818056667, 6.207679593624761, 6.579332246575679, 6.97323567654597, 7.390722033525779, 7.83320322308388, 8.302175681319744, 8.79922543569107, 9.326033468832199, 9.88438140350333, 10.476157527896651, 11.103363181676379, 11.768119524349979, 12.472674708860454, 13.219411484660288, 14.010855255980173, 14.84968262254465, 15.738730431619029, 16.68100537200059, 17.6796941424003, 18.73817422860383, 19.86002532585912, 21.049041445120196, 22.309243744089834, 23.64489412645407, 25.0605096553008, 26.560877829466868, 28.1510727744773, 29.836472402833405, 31.622776601683793});
    model.component("comp1").physics("plas").feature("eir2")
         .set("ytownratedata", new double[]{0, 3.8628461459937813E-45, 4.686406237188677E-45, 5.979017041825923E-45, 8.031689322115208E-45, 1.1360941346147565E-44, 1.6906819901352922E-44, 2.6425348858273714E-44, 4.328427214993E-44, 7.411323637856674E-44, 1.3230209883856822E-43, 2.455782394505858E-43, 4.7276546124925936E-43, 9.416083654102071E-43, 1.935826447981655E-42, 4.0992666485207764E-42, 8.923323734959668E-42, 1.9930969731721642E-41, 4.560411468512447E-41, 1.0677387086266105E-40, 2.5593932406056178E-40, 6.331961746252408E-40, 1.6987099785111306E-39, 6.095437152048297E-39, 3.99856950552678E-38, 3.970111255043148E-37, 4.131570672595342E-36, 3.938304685428063E-35, 3.345816162830509E-34, 2.5298748165861952E-33, 1.7101584908339047E-32, 1.0394367793294014E-31, 5.713815411947465E-31, 2.8568459017059962E-30, 1.3062670835066E-29, 5.490230443841932E-29, 2.1314133515495643E-28, 7.677938240525668E-28, 2.5773494197601064E-27, 8.093879960256801E-27, 2.3861724725448285E-26, 6.622514169806856E-26, 1.7332845583008644E-25, 4.2782451804736135E-25, 9.935852285137331E-25, 2.1604353408008714E-24, 4.370306196697126E-24, 8.188722772809043E-24, 1.423483187948503E-23, 2.315503714941046E-23, 3.56857365799681E-23, 5.2745296727524404E-23, 7.549198504121076E-23, 1.0534534010663927E-22, 1.4399224088752616E-22, 1.9339032278953891E-22, 2.557713925734705E-22, 3.336435913280873E-22, 4.297903843399094E-22, 5.472646147729282E-22, 6.893760275428349E-22, 8.596707306262385E-22, 1.0619010720505085E-21, 1.2999844461661301E-21, 1.5779496603789862E-21, 1.899869735917196E-21, 2.269780415475303E-21, 2.691584230174641E-21, 3.168940751672576E-21, 3.705144621755209E-21, 4.302994090374243E-21, 4.964654055618879E-21, 5.691518904036144E-21, 6.484081679374155E-21, 7.341817111493827E-21, 8.263086642456179E-21, 9.245073620433216E-21, 1.028375614941585E-20, 1.1373923601285132E-20, 1.250924052800546E-20, 1.3682358781415707E-20, 1.4885075299132823E-20, 1.6108529585814764E-20, 1.7343431798651347E-20, 1.8580309912808633E-20, 1.9809762997847076E-20, 2.102270734856458E-20, 2.221060308932263E-20, 2.3365650756406675E-20, 2.44809499814186E-20, 2.555061540729206E-20, 2.6569848022242147E-20, 2.7534962902360734E-20, 2.844337670175193E-20, 2.9293560005256696E-20, 3.008496083214912E-20, 3.0817906181658475E-20, 3.1493488613327995E-20, 3.2113444545848434E-20, 3.268003033040323E-20, 3.319590129957703E-20});
    model.component("comp1").physics("plas").feature("eir4").set("SpecifyReactionUsing", "UseLookupTable");
    model.component("comp1").physics("plas").feature("eir4").set("RateConstantForm", "UseTownsend");
    model.component("comp1").physics("plas").feature("eir4")
         .set("xtownratedata", new double[]{0, 0.1, 0.1059869818882502, 0.11233240329780274, 0.11905772393787836, 0.12618568830660204, 0.13374040261108217, 0.1417474162926805, 0.1502338084331859, 0.1592282793341092, 0.16876124757881478, 0.1788649529057435, 0.1895735652406376, 0.20092330025650468, 0.2129525418521362, 0.22570197196339203, 0.2392147081462638, 0.25353644939701114, 0.26871563070252286, 0.2848035868435802, 0.3018547260049922, 0.31992671377973836, 0.3390806681894053, 0.35938136638046275, 0.38089746369540706, 0.40370172585965547, 0.42787127506942646, 0.4534878508128582, 0.480638086306439, 0.509413801481638, 0.5399123135125903, 0.5722367659350217, 0.60649647746946, 0.6428073117284321, 0.6812920690579614, 0.7220809018385463, 0.7653117546501339, 0.8111308307896871, 0.8596930867190791, 0.9111627561154891, 0.9657139052966048, 1.0235310218990263, 1.0848096388007427, 1.1497569953977356, 1.2185927384710877, 1.2915496650148839, 1.3688745095370807, 1.4508287784959395, 1.537689634694013, 1.6297508346206442, 1.7273237219129889, 1.8307382802953678, 1.9403442495579148, 2.0565123083486516, 2.1796353277791214, 2.3101297000831593, 2.448436746822227, 2.595024211399736, 2.7503878409319458, 2.9150530628251756, 3.089576761729402, 3.2745491628777286, 3.4705958281810667, 3.6783797718286326, 3.898603702549072, 4.132012400115337, 4.379395234130494, 4.641588833612779, 4.919479916408218, 5.214008287999684, 5.52617001985409, 5.857020818056667, 6.207679593624761, 6.579332246575679, 6.97323567654597, 7.390722033525779, 7.83320322308388, 8.302175681319744, 8.79922543569107, 9.326033468832199, 9.88438140350333, 10.476157527896651, 11.103363181676379, 11.768119524349979, 12.472674708860454, 13.219411484660288, 14.010855255980173, 14.84968262254465, 15.738730431619029, 16.68100537200059, 17.6796941424003, 18.73817422860383, 19.86002532585912, 21.049041445120196, 22.309243744089834, 23.64489412645407, 25.0605096553008, 26.560877829466868, 28.1510727744773, 29.836472402833405, 31.622776601683793});
    model.component("comp1").physics("plas").feature("eir4")
         .set("ytownratedata", new double[]{0, -4.0402621125089875E-52, -4.901647347144891E-52, -6.2536262411559575E-52, -8.400575337756992E-52, -1.1882735979759825E-51, -1.7683330194574974E-51, -2.7639033957550954E-51, -4.5272267707648704E-51, -7.751716989461196E-51, -1.3837857816244148E-50, -2.568573582817624E-50, -4.944790211666106E-50, -9.848553268252029E-50, -2.024736673049623E-49, -4.287541129004246E-49, -9.333161405255873E-49, -2.0846371951866053E-48, -4.769848277272357E-48, -1.116712260766729E-47, -2.6748829892672844E-47, -6.569183876307805E-47, -1.6542715821642509E-46, -3.7190978632220775E-46, 1.777904471262869E-45, 9.918552950596114E-44, 2.8354252986949566E-42, 6.508190972574656E-41, 1.2473627165420715E-39, 2.023582592079532E-38, 2.80710600715147E-37, 3.360251601005251E-36, 3.500642590761032E-35, 3.19921745422967E-34, 2.5840912350603492E-33, 1.8577785846917688E-32, 1.196665982959641E-31, 6.949274173070568E-31, 3.659410497054549E-30, 1.7567630575416574E-29, 7.725644331355467E-29, 3.124897884644054E-28, 1.165947862018871E-27, 4.0176345184595885E-27, 1.2769117587697656E-26, 3.7285195388589126E-26, 9.948259219744444E-26, 2.417054828038973E-25, 5.36082354810924E-25, 1.0956510067576506E-24, 2.0910321571564604E-24, 3.7750514020792866E-24, 6.5144687149500716E-24, 1.0827326611653514E-23, 1.74250967415652E-23, 2.72588967246799E-23, 4.1568651792092005E-23, 6.193366799486193E-23, 9.032283024324673E-23, 1.2914252814792355E-22, 1.812803951616239E-22, 2.5014297048489465E-22, 3.396854137334009E-22, 4.54431385492262E-22, 5.994811346805137E-22, 7.805057091782122E-22, 1.0037250570298036E-21, 1.275867664973832E-21, 1.604109365557833E-21, 1.9959891265884085E-21, 2.4593001145943366E-21, 3.0019551825155986E-21, 3.631827223088716E-21, 4.3565665498859756E-21, 5.183399537782355E-21, 6.1189150033888125E-21, 7.16884697174777E-21, 8.337864223395537E-21, 9.62937798133665E-21, 1.1045378955725777E-20, 1.2586313516795564E-20, 1.4251006028113077E-20, 1.603663062666352E-20, 1.7938731534906543E-20, 1.9951287068766663E-20, 2.2066809625091893E-20, 2.4276472674429743E-20, 2.6570256367218137E-20, 2.893710553464351E-20, 3.136509694618879E-20, 3.384161572810282E-20, 3.6353542907483346E-20, 3.8887456462493126E-20, 4.142984684493897E-20, 4.396734503999304E-20, 4.648695760108188E-20, 4.8976299697091213E-20, 5.142381490434313E-20, 5.3818969832416577E-20, 5.615241283499498E-20, 5.84160887604268E-20});
    model.component("comp1").physics("plas").create("rxn1", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn1").set("formula", "Ars+Ars=>e+Ar+Ar+");
    model.component("comp1").physics("plas").feature("rxn1").set("kf", "3.734E8");
    model.component("comp1").physics("plas").create("rxn2", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn2").set("formula", "Ars+Ar=>Ar+Ar");
    model.component("comp1").physics("plas").feature("rxn2").set("kf", 1807);
    model.component("comp1").physics("plas").feature("Ar").set("FromMassConstraint", true);
    model.component("comp1").physics("plas").feature("Ar").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("Ars").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("Ar_1p").set("InitIon", true);
    model.component("comp1").physics("plas").feature("Ar_1p").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").create("wall1", "WallDriftDiffusion", 1);
    model.component("comp1").physics("plas").feature("wall1").selection().named("sel4");
    model.component("comp1").physics("plas").create("gnd1", "Ground", 1);
    model.component("comp1").physics("plas").feature("gnd1").selection().named("sel1");
    model.component("comp1").physics("plas").create("mct1", "MetalContact", 1);
    model.component("comp1").physics("plas").feature("mct1").selection().named("sel2");
    model.component("comp1").physics("plas").feature("mct1").set("V0", "V0");
    model.component("comp1").physics("plas").feature("mct1").set("CircuitType", "ParallelCBallastRCircuit");
    model.component("comp1").physics("plas").create("dct1", "DielectricContact", 1);
    model.component("comp1").physics("plas").feature("dct1").selection().named("sel3");
    model.component("comp1").physics("plas").create("sr1", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr1").set("formula", "Ar+=>Ar");
    model.component("comp1").physics("plas").feature("sr1").selection().named("sel1");
    model.component("comp1").physics("plas").feature("sr1").set("gammai", 0.25);
    model.component("comp1").physics("plas").feature("sr1").set("ebari", "plas.de_4-2*Wf");
    model.component("comp1").physics("plas").create("sr2", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr2").set("formula", "Ar+=>Ar");
    model.component("comp1").physics("plas").feature("sr2").selection().named("sel5");
    model.component("comp1").physics("plas").create("sr3", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr3").set("formula", "Ars=>Ar");
    model.component("comp1").physics("plas").feature("sr3").selection().named("sel4");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(3, 5, 6, 8, 10, 11, 14);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", 0.001);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("sel4");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "0 10^{range(-8,0.4,0)}");
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
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", 0.016, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 0, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 0.384, 1, 1);
    model.result().dataset().create("mir1", "Mirror2D");
    model.result("pg1").run();
    model.result("pg1").set("data", "mir1");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("data", "mir1");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("data", "mir1");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u8f74\u4e0a\u7535\u52bf");
    model.result("pg4").setIndex("looplevelinput", "last", 0);
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u8ddd\u79bb (x)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u7535\u52bf (V)");
    model.result("pg4").set("data", "cln1");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").set("expr", "V");
    model.result("pg4").feature("lngr1").set("descr", "\u7535\u52bf");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u8f74\u4e0a\u7535\u5b50\u6e29\u5ea6");
    model.result("pg5").setIndex("looplevelinput", "last", 0);
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u8ddd\u79bb (x)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u7535\u5b50\u6e29\u5ea6 (V)");
    model.result("pg5").set("data", "cln1");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("expr", "plas.Te");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u8f74\u4e0a\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg6").setIndex("looplevelinput", "last", 0);
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u8ddd\u79bb (x)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u7535\u5b50\u5bc6\u5ea6 (1/m<sup>3</sup>)");
    model.result("pg6").set("data", "cln1");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u8f74\u4e0a\u7684\u53d7\u6fc0\u6c29\u6570\u5bc6\u5ea6");
    model.result("pg7").setIndex("looplevelinput", "last", 0);
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "\u8ddd\u79bb (x)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u53d7\u6fc0\u6c29\u6570\u5bc6\u5ea6 (1/m<sup>3</sup>)");
    model.result("pg7").set("data", "cln1");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").set("expr", "plas.n_wArs");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u8f74\u4e0a\u7684\u6c29\u79bb\u5b50\u6570\u5bc6\u5ea6");
    model.result("pg8").setIndex("looplevelinput", "last", 0);
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "\u8ddd\u79bb (x)");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u6c29\u79bb\u5b50\u6570\u5bc6\u5ea6 (1/m<sup>3</sup>)");
    model.result("pg8").set("data", "cln1");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("linewidth", "preference");
    model.result("pg8").feature("lngr1").set("expr", "plas.n_wAr_1p");
    model.result("pg8").run();

    model.title("\u76f4\u6d41\u8f89\u5149\u653e\u7535");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u4e8c\u7ef4\u76f4\u6d41\u653e\u7535\uff0c\u5176\u4e2d\u4f7f\u7528\u7b80\u5355\u7684\u6c29\u6c14\u5316\u5b66\u53cd\u5e94\uff0c\u6c42\u89e3\u4e2d\u6027\u7269\u8d28\u548c\u5e26\u7535\u7269\u8d28\u7684\u8f93\u8fd0\u65b9\u7a0b\uff0c\u5e76\u4e0e\u6cca\u677e\u65b9\u7a0b\u5b9e\u73b0\u5168\u8026\u5408\u3002\u540c\u65f6\uff0c\u4e5f\u8003\u8651\u4e86\u79bb\u5b50\u51b2\u51fb\u5f15\u8d77\u7684\u7535\u5b50\u4e8c\u6b21\u53d1\u5c04\u73b0\u8c61\u3002\u672c\u6a21\u578b\u662f\u5b66\u4e60\u5982\u4f55\u4f7f\u7528\u201c\u7b49\u79bb\u5b50\u4f53\u201d\u63a5\u53e3\u6784\u5efa\u7b49\u79bb\u5b50\u4f53\u6a21\u578b\u7684\u5165\u95e8\u793a\u4f8b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("positive_column_2d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
